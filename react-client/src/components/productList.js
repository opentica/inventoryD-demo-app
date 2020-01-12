import React, { Component} from 'react'
import Grid from '@material-ui/core/Grid'
import TextField from '@material-ui/core/TextField'
import Product from './product';
import { ProductContext } from '../contexts/productListContext'




class ProductList  extends Component {
    static contextType = ProductContext
    
    
    state = {
        products: [],
        searchString: ''
    }
    constructor(props,context) {
        super(props,context)
        
        //console.log(this.context.products)
       
       
        

    }
    componentDidMount() {
       
        
         
       }
    getProducts = () => {
        
        console.log(this.props.context)
       
        
    }
    render() {
        return (
            <div>
            <TextField style={{padding: 24}}
                            id="searchInput"
                            placeholder="Search for Products"
                            margin="normal"
                            />
            <Grid container spacing={0} style={{padding: 0}}>
                {   
                    this.context.products.map(product => (
                        <Grid item xs={12} sm={6} lg={4} xl={3}>
                                    <Product product={product} description={product.productDescription} name={product.productName} manufacturerName={product.manufacturerName} manufacturerId={product.manufacturerId}  key={product.productId}/>
                        </Grid>
                    

                ))}
            </Grid>
            </div>
        )
    }
}

export default ProductList