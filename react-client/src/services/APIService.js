import axios from 'axios';

const USER_API_BASE_URL = 'http://localhost:8080/api';
let headers = new Headers();
headers.append('Content-Type', 'application/json');


class ApiService {


    getProducts() {
        return axios.get(USER_API_BASE_URL + '/product/' + 'getProducts');
       
        //return axios.get('https://jsonplaceholder.typicode.com/posts')
    }

    getProduct(id) {
        
    }

    deleteProduct(delProduct) {
        
        console.log(delProduct)
        return axios.delete(USER_API_BASE_URL + '/product/' + 'deleteProduct',{data:delProduct},headers);
       
    }
    deleteProducts() {
        return axios.delete(USER_API_BASE_URL + '/product/' + 'deleteAll');
       
    }

    addProduct(newProduct) {
        

        
        return axios.post(USER_API_BASE_URL + '/product/' + 'addProduct',newProduct,headers);
        
        
    }
    callRemoteServices(cityName)
    {
        return axios.get(USER_API_BASE_URL + '/open/getWeather?cityName='+cityName);
    }
    

    

}

export default new ApiService();