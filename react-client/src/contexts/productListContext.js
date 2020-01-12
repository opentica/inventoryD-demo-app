import React,{createContext, Component} from 'react'
import ApiService from '../services/APIService';
export const ProductContext = createContext();

export class ProductProvider extends Component {

    state = {
        myCompany: "optimiz",
        products: []
       
    }
    constructor() {
        super()
        //console.log("start ProductProvider consutrctor")
    }
    
    componentDidMount() {
       //console.log("load ProductProvider context")
        this.initializeProducts()
        
      }
    
    initializeProducts (){
        const initializeProducts=[{
            
            "productName":"coffee",
            "productDescription":"Capture the freshness with beans to espresso in under a minute.",
            "manufacturerName":"Bespresso",
            "manufacturerId":1,
            "sellerName":"DigitalShopee",
            "sellerId":1
        },
        {
            
            "productName":"piano",
            "productDescription":"Introduce your child to the wonder of music or learn how to play the piano ",
            "manufacturerName":"Casio",
            "manufacturerId":1,
            "sellerName":"DigitalShopee",
            "sellerId":1
        },
        {
            
            "productName":"laptop",
            "productDescription":"MacBook Pro packs powerful processors, a super-fast SSD and all-day battery life  ",
            "manufacturerName":"Casio",
            "manufacturerId":1,
            "sellerName":"DigitalShopee",
            "sellerId":1
        }

        ]
        let newProduct={
            "productName":"laptop",
            "productDescription":"MacBook Pro packs powerful processors, a super-fast SSD and all-day battery life  ",
            "manufacturerName":"Casio",
            "manufacturerId":1,
            "sellerName":"DigitalShopee",
            "sellerId":1
        };
        /*ApiService.deleteProducts()
        .then((res) => {
            
        });*/
        for(var i=0;i<initializeProducts.length;i++)
        ApiService.addProduct(initializeProducts[i])
        .then((res) => {
            
        });

        ApiService.getProducts()
        .then((res) => {
            this.setState({products:res.data});
            
        });
        ApiService.callRemoteServices("toronto")
        ApiService.callRemoteServices("chicago")
        ApiService.callRemoteServices("detroit")
       
        
        
        

    }

    updateProducts = (newProd) => {
        const updatedList=this.state.products
        
        ApiService.addProduct(newProd)
        .then((res) => {
            updatedList.push(newProd);
            this.setState({products:updatedList});
            
        });
       
        
    }
    removeProduct = (deleteProd) => {
        
        console.log(deleteProd);
        
        
        ApiService.deleteProduct(deleteProd)
        .then((res) => {
                ApiService.getProducts()
                .then((res) => {
                 this.setState({products:res.data});
            
                 });
        });
       
        
    }
    getProducts = () => {
        return this.products
    }
    
    removeAllProdcuts = () => {
        console.log("deleteProd inside remove all Products");
        
        
        
        ApiService.deleteProducts()
        .then((res) => {
                ApiService.getProducts()
                .then((res) => {
                 this.setState({products:res.data});
            
                 });
        });
       
        
    }


        
    render(){
        return (
            <ProductContext.Provider value={{...this.state,updateProducts:this.updateProducts,removeProduct:this.removeProduct,removeAllProdcuts:this.removeAllProdcuts}}>
                {this.props.children}
            </ProductContext.Provider>
    
        )
    }
        


    
}
