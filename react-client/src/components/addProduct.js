import React,{useState,useContext} from 'react'
import {ProductContext} from '../contexts/productListContext'

const AddProduct = () => {
    

    const {updateProducts}=useContext(ProductContext)
    const [name,setName]= useState('');
    const [manufacturer,setManufacturer]= useState('');

    const updateName = (e) => {
        setName(e.target.value)

    }

    const updateManufacturer = (e) => {
        setManufacturer(e.target.value)

    }

    const addProduct = (e) => {
        e.preventDefault();
        
        updateProducts({productName: name, manufacturerName: manufacturer})
    }
    
    
    return(
        <form onSubmit={addProduct}>
            <input type="text" name="name" value={name} onChange={updateName}/>
            <input type="text" name="manufacturer" value={manufacturer} onChange={updateManufacturer}/> 
            <button> Submit </button>
        </form>
        
    
        
    );
}
export default AddProduct;