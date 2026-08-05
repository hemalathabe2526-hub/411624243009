import React, { useState } from 'react';

function ColorChange() {
  const [color, setColor] = useState('white');  
  const changeColor = (newColor) => {
    setColor(newColor);
}

  return (
    <div style={{ backgroundColor: color, padding: '16px', borderRadius: '12px' }}>
      <h1>Color Change Example</h1>
      <button onClick={() => changeColor('red')}>Red</button>
      <button onClick={() => changeColor('blue')}>Blue</button>
      <button onClick={() => changeColor('green')}>Green</button>
    </div>
  );
}
export default ColorChange;