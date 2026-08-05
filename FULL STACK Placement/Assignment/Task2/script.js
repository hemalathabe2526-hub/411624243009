// Global state management
const appState = {
    users: [],
    apiCalls: 0,
    isLoading: false,
    fetchStartTime: null
};

// DOM Elements
const fetchBtn = document.getElementById('fetchBtn');
const clearBtn = document.getElementById('clearBtn');
const statusMessage = document.getElementById('statusMessage');
const loadingSpinner = document.getElementById('loadingSpinner');
const usersContainer = document.getElementById('usersContainer');
const totalUsersSpan = document.getElementById('totalUsers');
const apiCallsSpan = document.getElementById('apiCalls');
const fetchTimeSpan = document.getElementById('fetchTime');

// API URL
const API_URL = 'https://jsonplaceholder.typicode.com/users';

// Event Listeners
fetchBtn.addEventListener('click', handleFetchUsers);
clearBtn.addEventListener('click', handleClearData);

/**
 * Handle Fetch Users button click
 * Uses setTimeout for 2-second delay before fetching
 */
function handleFetchUsers() {
    if (appState.isLoading) return;

    appState.isLoading = true;
    appState.fetchStartTime = Date.now();
    fetchBtn.disabled = true;
    
    // Show loading state
    showLoadingState();
    
    // Update status
    updateStatusMessage('Starting fetch in 2 seconds...', 'loading');
    
    // 2-second delay using setTimeout
    setTimeout(() => {
        fetchUsersData();
    }, 2000);
}

/**
 * Fetch data from JSONPlaceholder API
 */
async function fetchUsersData() {
    try {
        updateStatusMessage('Fetching data from API...', 'loading');
        
        const response = await fetch(API_URL);
        
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        
        const data = await response.json();
        
        // Calculate fetch time
        const fetchTime = Date.now() - appState.fetchStartTime;
        
        // Update app state
        appState.users = data;
        appState.apiCalls += 1;
        
        // Hide loading spinner
        hideLoadingState();
        
        // Display users
        displayUsers(data);
        
        // Update status
        updateStatusMessage('✅ Loaded successfully!', 'success');
        
        // Update statistics
        updateStatistics(data.length, fetchTime);
        
        console.log('✅ Successfully fetched users:', data);
    } catch (error) {
        hideLoadingState();
        updateStatusMessage(`❌ Error: ${error.message}`, 'error');
        console.error('❌ Fetch error:', error);
        showEmptyState('Failed to load users. Please try again.');
    } finally {
        appState.isLoading = false;
        fetchBtn.disabled = false;
    }
}

/**
 * Display users on the webpage
 */
function displayUsers(users) {
    usersContainer.innerHTML = '';
    
    if (users.length === 0) {
        showEmptyState('No users found');
        return;
    }
    
    users.forEach((user, index) => {
        const userCard = createUserCard(user, index);
        usersContainer.appendChild(userCard);
    });
}

/**
 * Create a user card element
 */
function createUserCard(user, index) {
    const card = document.createElement('div');
    card.className = 'user-card';
    card.innerHTML = `
        <span class="user-id">ID: ${user.id}</span>
        <div class="user-name">${escapeHtml(user.name)}</div>
        <div class="user-info">
            <span class="user-info-icon">✉️</span>
            <strong>Email:</strong>
            <span>${escapeHtml(user.email)}</span>
        </div>
        <div class="user-info">
            <span class="user-info-icon">📞</span>
            <strong>Phone:</strong>
            <span>${escapeHtml(user.phone)}</span>
        </div>
    `;
    return card;
}

/**
 * Show empty state message
 */
function showEmptyState(message) {
    usersContainer.innerHTML = `
        <div class="empty-state" style="grid-column: 1 / -1;">
            <div class="empty-state-icon">📭</div>
            <p>${message}</p>
        </div>
    `;
}

/**
 * Update status message
 */
function updateStatusMessage(message, status = '') {
    statusMessage.textContent = message;
    statusMessage.className = 'status-message';
    if (status) {
        statusMessage.classList.add(status);
    }
}

/**
 * Show loading spinner
 */
function showLoadingState() {
    loadingSpinner.classList.remove('hidden');
    usersContainer.innerHTML = '';
}

/**
 * Hide loading spinner
 */
function hideLoadingState() {
    loadingSpinner.classList.add('hidden');
}

/**
 * Update statistics
 */
function updateStatistics(userCount, fetchTime) {
    totalUsersSpan.textContent = userCount;
    apiCallsSpan.textContent = appState.apiCalls;
    fetchTimeSpan.textContent = `${fetchTime}ms`;
}

/**
 * Handle Clear Data button click
 */
function handleClearData() {
    appState.users = [];
    appState.apiCalls = 0;
    appState.fetchStartTime = null;
    
    usersContainer.innerHTML = '';
    statusMessage.textContent = 'Ready to fetch data...';
    statusMessage.className = 'status-message';
    loadingSpinner.classList.add('hidden');
    
    totalUsersSpan.textContent = '0';
    apiCallsSpan.textContent = '0';
    fetchTimeSpan.textContent = '0ms';
    
    console.log('🗑️ Data cleared');
}

/**
 * Escape HTML to prevent XSS attacks
 */
function escapeHtml(text) {
    const map = {
        '&': '&amp;',
        '<': '&lt;',
        '>': '&gt;',
        '"': '&quot;',
        "'": '&#039;'
    };
    return text.replace(/[&<>"']/g, m => map[m]);
}

// Initialize on page load
document.addEventListener('DOMContentLoaded', () => {
    console.log('📱 User Fetcher Application Loaded');
    updateStatusMessage('Ready to fetch data...');
});
