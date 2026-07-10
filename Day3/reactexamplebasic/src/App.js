// import First from './First';
// import Second from './Second';
// import Parent from './Parent';
// import Buttons from './Atoms/Buttons';
// import Page from './Pages/Page';
// import Heading from './Atoms/Heading';
// import Operations from './Operations';
// import ColorChange from './ColorChange';
// import SearchBar from './Molecules/SearchBar';
// import Card from './Organisms/Card';
// import InputFields from './Atoms/InputFields';
// import BluePrint from './Templates/BluePrint';

// function App() {
//   return (
//     <>
//       <Heading/>
//       <First/>
//       <Second/>
//       <Parent/>
//       <Page/>
//       <Buttons/>
//       <Operations/>
//       <ColorChange/>
//       <SearchBar/>
//       <Card/><br/>
//       <BluePrint name="Hemalatha B E"/>
//       <InputFields/>
//       <Operations/>
//     </>
//   );
// }

// export default App;



import Operations from './Operations';
import {Add, Subtract, Multiply} from './Math';
import {Route, Routes} from 'react-router-dom';
import NavBar from './NavBar';

function App() {
  return (
    <>
    <Routes>
      <Route path="/add" element={<Add/>}/>
      <Route path="/subtract" element={<Subtract/>}/>
      <Route path="/multiply" element={<Multiply/>}/>
      <Route path="/operations" element={<Operations/>}/> 
      <Route path="*" element={<h1>404 Not Found Incorrect Path</h1>}/>
      <Route path="/" element={<h1>Welcome to React Router</h1>}/>
    </Routes>
    <NavBar/>
      {/* <Add/>
      <Subtract/>
      <Multiply/> */}
      {/* <Operations/> */}
    </>
  );
}

export default App;