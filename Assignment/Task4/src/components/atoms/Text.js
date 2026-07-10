import React from 'react';

export default function Text({ as: Tag = 'p', variant = 'body', className = '', children }) {
  return React.createElement(Tag, { className: `atom-text atom-text--${variant} ${className}`.trim() }, children);
}