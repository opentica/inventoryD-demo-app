import React,{useState,useContext} from 'react'
import {ProductContext,} from '../contexts/productListContext'


import AppBar from '@material-ui/core/AppBar'
import Toolbar from '@material-ui/core/Toolbar'
import Typography from '@material-ui/core/Typography'
import { makeStyles } from '@material-ui/core/styles';
import Menu from '@material-ui/core/Menu';
import MenuItem from '@material-ui/core/MenuItem';
import Button from '@material-ui/core/Button';

import IconButton from '@material-ui/core/IconButton';
import MenuIcon from '@material-ui/icons/Menu';

const useStyles = makeStyles(theme => ({
    root: {
      flexGrow: 1,
    },
    menuButton: {
      marginRight: theme.spacing(2),
    },
    title: {
      flexGrow: 1,
    },
  }));

  
  
const Nav = () => {
    const {updateProducts}=useContext(ProductContext)
    const {removeAllProdcuts}=useContext(ProductContext)

    const [anchorEl, setAnchorEl] = React.useState(null);
    const classes = useStyles();

  const handleClick = event => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  const addProduct = () => {
      
    updateProducts({productName: "laptop", manufacturerName: "Apple"})


  };
  
    return (
        <div className={classes.root}>
            <AppBar position="static">
            
         <Menu
        id="simple-menu"
        anchorEl={anchorEl}
        keepMounted
        open={Boolean(anchorEl)}
        onClose={handleClose}
      >
        <MenuItem onClick={handleClose}>AppDynamics Performance Scenario</MenuItem>
        <MenuItem onClick={handleClose}>Moogsoft with AppDynamcis Integration</MenuItem>
        <MenuItem onClick={handleClose}>End to End AIPs</MenuItem>
        <MenuItem onClick={handleClose} onClick={addProduct}>Add Product</MenuItem>
        <MenuItem onClick={handleClose} onClick={removeAllProdcuts} >Delete All Products</MenuItem>
      </Menu>
                <Toolbar>
                <IconButton edge="start" className={classes.menuButton} color="inherit" aria-label="menu" onClick={handleClick}>
            <MenuIcon />
          </IconButton>
                <Button color="inherit" onClick={handleClick}>Generate Demo Use Cases</Button>
                
                <Typography variant="h6" className={classes.title}>
            Inventory Demo Application
          </Typography>
                    <Button color="inherit" onClick={addProduct}>Add Product </Button>
                    
                </Toolbar>
                
            </AppBar>
        </div>
    )
}
export default Nav;

