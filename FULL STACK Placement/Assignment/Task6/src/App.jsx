import React from 'react';
import { BrowserRouter, Link, Route, Routes } from 'react-router-dom';
import { authors, books, bookstoreStats } from './data';

function Layout({ children }) {
  return (
    <div className="app-shell">
      <div className="orb orb-one" />
      <div className="orb orb-two" />
      <header className="topbar">
        <Link to="/" className="brand">
          <span className="brand-mark">B</span>
          <span>
            <strong>BookNest</strong>
            <small>Manual route bookstore</small>
          </span>
        </Link>
        <nav className="topnav" aria-label="Primary">
          <Link to="/">Home</Link>
          <Link to="/books">Books</Link>
          <Link to="/authors">Authors</Link>
          <Link to="/about">About</Link>
        </nav>
      </header>
      <main className="page-wrap">{children}</main>
    </div>
  );
}

function PageHero({ kicker, title, description, action, secondary }) {
  return (
    <section className="hero panel-card">
      <div>
        <p className="eyebrow">{kicker}</p>
        <h1>{title}</h1>
        <p className="lead">{description}</p>
      </div>
      <div className="hero-actions">
        {action}
        {secondary}
      </div>
    </section>
  );
}

function HomePage() {
  return (
    <div className="stack">
      <PageHero
        kicker="Welcome"
        title="A brighter way to explore books and authors."
        description="Discover a curated shelf, jump to manual book pages, and browse author stories without relying on dynamic route params."
        action={<Link to="/books" className="button button-primary">Browse Books</Link>}
        secondary={<Link to="/authors" className="button button-secondary">Meet Authors</Link>}
      />

      <section className="stats-grid">
        {bookstoreStats.map((stat) => (
          <article key={stat.label} className="panel-card stat-card">
            <p className="eyebrow">{stat.label}</p>
            <strong>{stat.value}</strong>
          </article>
        ))}
      </section>

      <section className="panel-card spotlight">
        <div>
          <p className="eyebrow">Spotlight</p>
          <h2>Curated reading lanes</h2>
          <p>
            Each page is hand-linked so the experience stays clear, testable, and easy to
            inspect in class.
          </p>
        </div>
        <div className="spotlight-links">
          <Link to="/books" className="text-link">Go to books</Link>
          <Link to="/authors" className="text-link">Go to authors</Link>
          <Link to="/about" className="text-link">Learn more</Link>
        </div>
      </section>
    </div>
  );
}

function BooksPage() {
  return (
    <div className="stack">
      <PageHero
        kicker="Books"
        title="Six books, six manual detail pages."
        description="Every title below links to its own page with description, author, genre, and simple next/previous navigation."
      />
      <section className="grid cards-grid">
        {books.map((book, index) => (
          <article key={book.slug} className="panel-card book-card">
            <p className="eyebrow">Book {index + 1}</p>
            <h2>{book.title}</h2>
            <p className="meta">by {book.author}</p>
            <p>{book.genre}</p>
            <Link to={`/books/${book.slug}`} className="text-link">Open book page</Link>
          </article>
        ))}
      </section>
    </div>
  );
}

function BookDetailPage({ book, previousBook, nextBook }) {
  return (
    <div className="stack">
      <section className="panel-card detail-card">
        <div>
          <p className="eyebrow">Book detail</p>
          <h1>{book.title}</h1>
          <p className="meta">Author: {book.author}</p>
          <p className="meta">Genre: {book.genre}</p>
          <p className="detail-copy">{book.description}</p>
        </div>
        <div className="detail-nav">
          <Link to="/books" className="button button-secondary">Back to Books</Link>
          <div className="inline-links">
            <Link to={`/books/${previousBook.slug}`} className="text-link">Previous</Link>
            <Link to={`/books/${nextBook.slug}`} className="text-link">Next</Link>
          </div>
        </div>
      </section>
    </div>
  );
}

function AuthorsPage() {
  return (
    <div className="stack">
      <PageHero
        kicker="Authors"
        title="Meet the voices behind the shelf."
        description="Each author card links to a dedicated page with famous books, country, and a short biography."
      />
      <section className="grid cards-grid">
        {authors.map((author) => (
          <article key={author.slug} className="panel-card author-card">
            <p className="eyebrow">Author</p>
            <h2>{author.name}</h2>
            <p className="meta">{author.country}</p>
            <p>{author.famousBooks.join(', ')}</p>
            <Link to={`/authors/${author.slug}`} className="text-link">Open author page</Link>
          </article>
        ))}
      </section>
    </div>
  );
}

function AuthorDetailPage({ author }) {
  return (
    <div className="stack">
      <section className="panel-card detail-card">
        <div>
          <p className="eyebrow">Author detail</p>
          <h1>{author.name}</h1>
          <p className="meta">Country: {author.country}</p>
          <p className="meta">Famous books: {author.famousBooks.join(', ')}</p>
          <p className="detail-copy">{author.biography}</p>
        </div>
        <div className="detail-nav">
          <Link to="/authors" className="button button-secondary">Back to Authors</Link>
        </div>
      </section>
    </div>
  );
}

function AboutPage() {
  return (
    <div className="stack">
      <PageHero
        kicker="About"
        title="Built as a clean routing demo for a bookstore concept."
        description="This project uses only BrowserRouter, Routes, Route, and Link for navigation, plus static data for predictable manual pages."
      />
      <section className="panel-card about-card">
        <h2>What makes this version different</h2>
        <ul className="feature-list">
          <li>Hand-crafted book and author routes.</li>
          <li>Previous and next links on every book page.</li>
          <li>Distinct visual system with a spotlight section and stat cards.</li>
          <li>Extra 404 page to keep navigation polished.</li>
        </ul>
        <div className="inline-links">
          <Link to="/" className="text-link">Return Home</Link>
          <Link to="/books" className="text-link">Explore Books</Link>
        </div>
      </section>
    </div>
  );
}

function NotFoundPage() {
  return (
    <section className="panel-card notfound-card">
      <p className="eyebrow">404</p>
      <h1>Page Not Found</h1>
      <p>The shelf you were looking for does not exist.</p>
      <Link to="/" className="button button-primary">Return Home</Link>
    </section>
  );
}

function AppRoutes() {
  const bookRoutes = books.map((book, index) => {
    const previousBook = books[(index - 1 + books.length) % books.length];
    const nextBook = books[(index + 1) % books.length];

    return (
      <Route
        key={book.slug}
        path={`/books/${book.slug}`}
        element={<BookDetailPage book={book} previousBook={previousBook} nextBook={nextBook} />}
      />
    );
  });

  const authorRoutes = authors.map((author) => (
    <Route
      key={author.slug}
      path={`/authors/${author.slug}`}
      element={<AuthorDetailPage author={author} />}
    />
  ));

  return (
    <Routes>
      <Route path="/" element={<HomePage />} />
      <Route path="/books" element={<BooksPage />} />
      {bookRoutes}
      <Route path="/authors" element={<AuthorsPage />} />
      {authorRoutes}
      <Route path="/about" element={<AboutPage />} />
      <Route path="*" element={<NotFoundPage />} />
    </Routes>
  );
}

export default function App() {
  return (
    <BrowserRouter>
      <Layout>
        <AppRoutes />
      </Layout>
    </BrowserRouter>
  );
}