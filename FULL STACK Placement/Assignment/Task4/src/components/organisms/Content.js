import React, { useEffect, useState } from 'react';
import themeService from '../../services/ThemeService';
import Text from '../atoms/Text';

export default function Content() {
  const [theme, setTheme] = useState(themeService.getTheme());

  useEffect(() => {
    const handleThemeChange = (nextTheme) => setTheme(nextTheme);
    themeService.attach(handleThemeChange);

    return () => themeService.detach(handleThemeChange);
  }, []);

  return React.createElement(
    'section',
    { className: 'section-card content', 'data-theme': theme.mode },
    React.createElement(
      'div',
      { className: 'content__hero' },
      React.createElement(Text, { as: 'h2', variant: 'subtitle', className: 'section-subtitle' }, 'Content Area'),
      React.createElement(
        Text,
        { as: 'p', variant: 'body', className: 'section-description' },
        'The content block responds automatically to the shared service.',
      ),
    ),
    React.createElement(
      'div',
      { className: 'content__grid' },
      React.createElement(
        'article',
        { className: 'content-card content-card--accent' },
        React.createElement(Text, { as: 'p', variant: 'eyebrow' }, 'Current Mode'),
        React.createElement(Text, { as: 'p', variant: 'value' }, theme.mode.toUpperCase()),
      ),
      React.createElement(
        'article',
        { className: 'content-card' },
        React.createElement(Text, { as: 'p', variant: 'eyebrow' }, 'UI Behavior'),
        React.createElement(
          Text,
          { as: 'p', variant: 'body' },
          'Header, Theme Panel, Content, and Footer all subscribe to the same observer service and update independently with useState.',
        ),
      ),
    ),
  );
}