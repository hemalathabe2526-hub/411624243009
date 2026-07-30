const messageInput = document.getElementById('messageInput');
const addButton = document.getElementById('addButton');
const clearButton = document.getElementById('clearButton');
const charCount = document.getElementById('charCount');
const activeCount = document.getElementById('activeCount');
const messagesList = document.getElementById('messagesList');
const statusMessage = document.getElementById('statusMessage');

const messages = [];

function setStatus(message, type = 'info') {
  statusMessage.textContent = message;
  statusMessage.className = 'status';

  if (type === 'success') {
    statusMessage.classList.add('success');
  } else if (type === 'error') {
    statusMessage.classList.add('error');
  }
}

function updateCharCount() {
  const length = messageInput.value.length;
  charCount.textContent = `Character count: ${length}`;

  charCount.classList.remove('warning', 'good');
  if (length > 0 && length < 3) {
    charCount.classList.add('warning');
  } else if (length >= 3) {
    charCount.classList.add('good');
  }
}

function renderMessages() {
  messagesList.innerHTML = '';
  activeCount.textContent = `Active messages: ${messages.length}`;

  if (messages.length === 0) {
    const emptyState = document.createElement('p');
    emptyState.className = 'empty-state';
    emptyState.textContent = 'No active messages yet. Add one to start the board.';
    messagesList.appendChild(emptyState);
    return;
  }

  messages.forEach((message) => {
    const card = document.createElement('article');
    card.className = 'message-card';

    const text = document.createElement('p');
    text.className = 'message-text';
    text.textContent = message.text;

    const meta = document.createElement('p');
    meta.className = 'message-meta';
    meta.textContent = `Added at ${message.timestamp} • Expires in 10 seconds`;

    card.appendChild(text);
    card.appendChild(meta);
    messagesList.appendChild(card);
  });
}

function removeMessage(id) {
  const index = messages.findIndex((message) => message.id === id);
  if (index === -1) {
    return;
  }

  messages.splice(index, 1);
  renderMessages();
  setStatus('Message Expired', 'error');
}

function clearAllMessages() {
  messages.splice(0, messages.length);
  renderMessages();
  setStatus('All Messages Cleared', 'success');
}

addButton.addEventListener('click', () => {
  const messageText = messageInput.value.trim();

  const promise = new Promise((resolve, reject) => {
    setTimeout(() => {
      if (messageText.length >= 3) {
        resolve(messageText);
      } else {
        reject(new Error('Message must contain at least 3 characters'));
      }
    }, 300);
  });

  promise
    .then((resolvedMessage) => {
      const messageObject = {
        id: Date.now().toString(),
        text: resolvedMessage,
        timestamp: new Date().toLocaleTimeString([], {
          hour: '2-digit',
          minute: '2-digit',
        }),
      };

      messages.push(messageObject);
      renderMessages();
      setStatus('Message Added Successfully', 'success');
      messageInput.value = '';
      updateCharCount();

      setTimeout(() => removeMessage(messageObject.id), 10000);
    })
    .catch((error) => {
      setStatus(error.message, 'error');
    });
});

clearButton.addEventListener('click', clearAllMessages);
messageInput.addEventListener('input', updateCharCount);
messageInput.addEventListener('keydown', (event) => {
  if (event.key === 'Enter') {
    addButton.click();
  }
});
updateCharCount();
renderMessages();