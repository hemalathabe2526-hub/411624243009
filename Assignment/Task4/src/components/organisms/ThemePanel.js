import React, { useEffect, useState } from 'react';
import themeService from '../../services/ThemeService';
import Button from '../atoms/Button';
import Text from '../atoms/Text';
import ThemeLabel from '../molecules/ThemeLabel';

export default function ThemePanel() {
  const [theme, setTheme] = useState(themeService.getTheme());

  useEffect(() => {
    const handleThemeChange = (nextTheme) => setTheme(nextTheme);
    themeService.attach(handleThemeChange);

    return () => themeService.detach(handleThemeChange);
  }, []);

  return React.createElement(
    'section',
    { className: 'section-card panel', 'data-theme': theme.mode },
    React.createElement(
      'div',
      { className: 'panel__copy' },
      React.createElement(Text, { as: 'h2', variant: 'subtitle', className: 'section-subtitle' }, 'Theme Panel'),
      React.createElement(ThemeLabel, { theme }),
      React.createElement(
        Text,
        { as: 'p', variant: 'body', className: 'section-description' },
        'Press the toggle button to switch every subscribing component between light and dark themes.',
      ),
    ),
    React.createElement(
      Button,
      { onClick: () => themeService.toggleTheme(), ariaLabel: 'Toggle theme' },
      theme.mode === 'light' ? 'Switch to Dark' : 'Switch to Light',
    ),
  );
}