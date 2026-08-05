import React from 'react';

export default function Button({ children, onClick, type = 'button', className = '', ariaLabel }) {
  return React.createElement(
    'button',
    {
      type,
      className: `atom-button ${className}`.trim(),
      onClick,
      'aria-label': ariaLabel,
    },
    children,
  );
}