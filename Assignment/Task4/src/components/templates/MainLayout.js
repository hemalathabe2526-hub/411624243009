import React from 'react';
export default function MainLayout({ children }) {
  return React.createElement(
    'main',
    { className: 'app-shell' },
    React.createElement('div', { className: 'background-orb background-orb--one', 'aria-hidden': 'true' }),
    React.createElement('div', { className: 'background-orb background-orb--two', 'aria-hidden': 'true' }),
    React.createElement('div', { className: 'layout-stack' }, children),
  );
}