import React, { useEffect, useState } from 'react';
import themeService from '../../services/ThemeService';
import Text from '../atoms/Text';

export default function Header() {
  const [theme, setTheme] = useState(themeService.getTheme());

  useEffect(() => {
    const handleThemeChange = (nextTheme) => setTheme(nextTheme);
    themeService.attach(handleThemeChange);

    return () => themeService.detach(handleThemeChange);
  }, []);

  return React.createElement(
    'header',
    { className: 'section-card section-card--compact header', 'data-theme': theme.mode },
    React.createElement(
      'div',
      null,
      React.createElement(Text, { as: 'p', variant: 'eyebrow', className: 'section-kicker' }, 'Observer Pattern'),
      React.createElement(Text, { as: 'h1', variant: 'title', className: 'section-title' }, 'Theme Switcher'),
    ),
    React.createElement(
      'div',
      { className: 'theme-badge', 'aria-label': `Current theme is ${theme.label}` },
      React.createElement('span', { className: 'theme-badge__dot', 'aria-hidden': 'true' }),
      React.createElement('span', null, theme.label),
    ),
  );
}