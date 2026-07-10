import React from 'react';
import Text from '../atoms/Text';

export default function ThemeLabel({ theme }) {
  return React.createElement(
    'div',
    { className: 'theme-label' },
    React.createElement('span', { className: 'theme-label__dot', 'aria-hidden': 'true' }),
    React.createElement(
      'div',
      null,
      React.createElement(
        Text,
        { as: 'p', variant: 'eyebrow', className: 'theme-label__eyebrow' },
        'Active theme',
      ),
      React.createElement(
        Text,
        { as: 'p', variant: 'body', className: 'theme-label__value' },
        theme.label,
      ),
    ),
  );
}