import React,{useContext} from 'react'
import {ProductContext,} from '../contexts/productListContext'

import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';

const useStyles = makeStyles({
    card: {
      maxWidth: 300,
    },
    media: {
      height: 140,
    },
  });
  const images = {
    "coffee": "https://i.picsum.photos/id/1060/5598/3732.jpg",
    "piano": "https://i.picsum.photos/id/1082/5416/3611.jpg",
    "laptop": "https://i.picsum.photos/id/119/3264/2176.jpg",

  };
  
const Product = ({product}) => {
    
   
   
    const classes = useStyles();
    const {removeProduct}=useContext(ProductContext)
    const deleteProduct = () => {
        const deleteProdObj={
            "productId": 245,
        "productName": "laptop",
        "productDescription": "MacBook Pro packs powerful processors, a super-fast SSD and all-day battery life  ",
        "manufacturerName": "Casio",
        "manufacturerId": 1,
        "sellerName": "DigitalShopee",
        "sellerId": 1
        }
        
        removeProduct(product)
        
    
    
      };

  return (
    <Card className={classes.card}>
      <CardActionArea>
        <CardMedia
          className={classes.media}
          image={images[product.productName]}
          title={product.productName}
        />
        <CardContent>
          <Typography gutterBottom variant="h5" component="h2">
          {product.productName} 
          </Typography>
          <Typography variant="body2" color="textSecondary" component="p">
          {product.productDescription}
          </Typography>
        </CardContent>
      </CardActionArea>
      <CardActions>
        <Button size="small" color="primary" onClick={deleteProduct}>
          Delete
        </Button>
        
      </CardActions>
    </Card>
  );
}
export default Product;

