import React from 'react';
import Footer from '../organisms/Footer';
import Header from '../organisms/Header';
import Content from '../organisms/Content';
import ThemePanel from '../organisms/ThemePanel';
import MainLayout from '../templates/MainLayout';

export default function HomePage() {
  return React.createElement(
    MainLayout,
    null,
    React.createElement(Header),
    React.createElement(ThemePanel),
    React.createElement(Content),
    React.createElement(Footer),
  );
}