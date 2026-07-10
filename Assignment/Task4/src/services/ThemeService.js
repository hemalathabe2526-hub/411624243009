const THEMES = {
  light: {
    key: 'light',
    label: 'Light Theme',
    shortLabel: 'Light',
    mode: 'light',
    background: 'linear-gradient(135deg, #fff7ed 0%, #fef3c7 45%, #ffffff 100%)',
    surface: 'rgba(255, 255, 255, 0.8)',
    surfaceStrong: 'rgba(255, 255, 255, 0.96)',
    text: '#1f2937',
    muted: '#6b7280',
    accent: '#d97706',
    accentSoft: '#fde68a',
    border: 'rgba(217, 119, 6, 0.2)',
    shadow: '0 24px 60px rgba(180, 83, 9, 0.14)',
    heroGlow: 'radial-gradient(circle at top right, rgba(251, 191, 36, 0.35), transparent 40%)',
  },
  dark: {
    key: 'dark',
    label: 'Dark Theme',
    shortLabel: 'Dark',
    mode: 'dark',
    background: 'linear-gradient(135deg, #111827 0%, #1f2937 45%, #0f172a 100%)',
    surface: 'rgba(15, 23, 42, 0.78)',
    surfaceStrong: 'rgba(17, 24, 39, 0.94)',
    text: '#f8fafc',
    muted: '#cbd5e1',
    accent: '#60a5fa',
    accentSoft: '#1d4ed8',
    border: 'rgba(96, 165, 250, 0.24)',
    shadow: '0 28px 60px rgba(15, 23, 42, 0.45)',
    heroGlow: 'radial-gradient(circle at top right, rgba(59, 130, 246, 0.35), transparent 40%)',
  },
};

class ThemeService {
  constructor() {
    this.themeKey = 'light';
    this.listeners = new Set();
  }

  attach(listener) {
    this.listeners.add(listener);
  }

  detach(listener) {
    this.listeners.delete(listener);
  }

  notify() {
    const snapshot = this.getTheme();
    this.listeners.forEach((listener) => listener(snapshot));
  }

  toggleTheme() {
    this.themeKey = this.themeKey === 'light' ? 'dark' : 'light';
    this.notify();
  }

  getTheme() {
    return { ...THEMES[this.themeKey] };
  }
}

const themeService = new ThemeService();

export { THEMES, ThemeService };
export default themeService;
