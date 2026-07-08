import First from './First';
import Second from './Second';
import Parent from './Parent';
import Buttons from './Atoms/Buttons';
import Page from './Pages/Page';
import Heading from './Atoms/Heading';
import Operations from './Operations';
import ColorChange from './ColorChange';
import SearchBar from './Molecules/SearchBar';
import Card from './Organisms/Card';
import InputFields from './Atoms/InputFields';
import BluePrint from './Templates/BluePrint';

function App() {
  return (
    <>
      <Heading/>
      <First/>
      <Second/>
      <Parent/>
      <Page/>
      <Buttons/>
      <Operations/>
      <ColorChange/>
      <SearchBar/>
      <Card/><br/>
      <BluePrint name="Hemalatha B E"/>
      <InputFields/>
      <Operations/>
    </>
  );
}

export default App;