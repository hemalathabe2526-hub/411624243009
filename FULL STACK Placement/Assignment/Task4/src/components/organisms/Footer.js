import React, { useEffect, useState } from 'react';
import themeService from '../../services/ThemeService';
import Text from '../atoms/Text';

export default function Footer() {
  const [theme, setTheme] = useState(themeService.getTheme());

  useEffect(() => {
    const handleThemeChange = (nextTheme) => setTheme(nextTheme);
    themeService.attach(handleThemeChange);

    return () => themeService.detach(handleThemeChange);
  }, []);

  return React.createElement(
    'footer',
    { className: 'section-card section-card--compact footer', 'data-theme': theme.mode },
    React.createElement(Text, { as: 'p', variant: 'body', className: 'footer__text' }, `Footer synced to ${theme.label}`),
    React.createElement(Text, { as: 'p', variant: 'eyebrow', className: 'footer__note' }, 'No Context API. No Redux. Pure Observer Pattern.'),
  );
}