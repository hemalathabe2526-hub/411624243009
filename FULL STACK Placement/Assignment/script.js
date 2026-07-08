document.addEventListener('DOMContentLoaded', function () {
  const body = document.body;
  const navLinks = document.querySelectorAll('.main-nav a');
  const statusBox = document.getElementById('status-box');
  const gridSection = document.querySelector('.grid-section');
  const image = document.querySelector('.rounded-image');
  const video = document.querySelector('video');
  const greetButtons = [
    document.getElementById('primaryAction'),
    document.getElementById('greetBtn'),
  ];
  const toggleThemeButton = document.getElementById('themeBtn');
  const shuffleGridButton = document.getElementById('shuffleBtn');
  const toggleImageButton = document.getElementById('toggleImage');
  const playPauseButton = document.getElementById('playPauseVideo');
  const table = document.querySelector('.table-section table');
  const gridItems = document.querySelectorAll('.grid-item');

  function updateStatus(message) {
    statusBox.textContent = `Status: ${message}`;
    statusBox.classList.add('greeting');
    setTimeout(() => statusBox.classList.remove('greeting'), 600);
  }

  function showGreeting() {
    const greetings = [
      'Hello, welcome to the interactive demo!',
      'Nice to see you here — try the buttons above.',
      'Ready to explore? Shuffle the grid or toggle the theme!',
    ];
    const message = greetings[Math.floor(Math.random() * greetings.length)];
    updateStatus(message);
  }

  navLinks.forEach((link) => {
    link.addEventListener('click', (event) => {
      event.preventDefault();
      navLinks.forEach((item) => item.classList.remove('active-link'));
      link.classList.add('active-link');
      updateStatus(`Navigated to ${link.textContent}`);
    });
  });

  greetButtons.forEach((button) => {
    button.addEventListener('click', showGreeting);
  });

  toggleThemeButton.addEventListener('click', () => {
    body.classList.toggle('dark-mode');
    const mode = body.classList.contains('dark-mode') ? 'Dark' : 'Light';
    updateStatus(`${mode} theme enabled`);
  });

  shuffleGridButton.addEventListener('click', () => {
    const items = Array.from(gridSection.children);
    const shuffled = items
      .map((item) => ({ item, sort: Math.random() }))
      .sort((a, b) => a.sort - b.sort)
      .map(({ item }) => item);

    shuffled.forEach((item) => gridSection.appendChild(item));
    updateStatus('Grid layout shuffled');
  });

  toggleImageButton.addEventListener('click', () => {
    const isSoft = image.classList.toggle('soft-rounded');
    image.classList.toggle('strong-shadow', isSoft);
    updateStatus(isSoft ? 'Image style softened' : 'Image style restored');
  });

  playPauseButton.addEventListener('click', () => {
    if (video.paused) {
      video.play();
      playPauseButton.textContent = 'Pause Video';
      updateStatus('Video playing');
    } else {
      video.pause();
      playPauseButton.textContent = 'Play / Pause Video';
      updateStatus('Video paused');
    }
  });

  gridItems.forEach((item) => {
    item.addEventListener('click', () => {
      gridItems.forEach((cell) => cell.classList.remove('grid-item--active'));
      item.classList.add('grid-item--active');
      updateStatus(`Selected grid item ${item.textContent.trim()}`);
    });
  });

  table.querySelectorAll('th').forEach((header, index) => {
    header.addEventListener('click', () => {
      const rows = Array.from(table.tBodies[0].rows);
      rows.sort((a, b) => {
        const first = a.cells[index].textContent.trim();
        const second = b.cells[index].textContent.trim();
        return first.localeCompare(second, undefined, { numeric: true });
      });
      rows.forEach((row) => table.tBodies[0].appendChild(row));
      updateStatus(`Table sorted by column ${index + 1}`);
    });
  });

  const footerYear = document.querySelector('#footer-year');
  if (footerYear) {
    footerYear.textContent = new Date().getFullYear();
  }
});
