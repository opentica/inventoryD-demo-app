import React from 'react';
import './App.css';

import ProductList from './components/productList';
import Nav from './components/nav';
import AddProduct from './components/addProduct';
import {ProductProvider} from './contexts/productListContext'
/*

*/

function App() {
  return (
    <ProductProvider>
      <div className="App">
        <Nav/>
        <ProductList/>
      </div>
    </ProductProvider>
  );
}

export default App;
