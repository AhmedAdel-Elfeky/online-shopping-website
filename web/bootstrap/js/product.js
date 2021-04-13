/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




let totalPrice = 0;
let productInCart = 0;
let product_id = 0;
let result = 1;

function reloadPage() {
    if (event.persisted) {
        window.location.reload();
    }
}

function addToCart(price, id, name) {
    if (checkCookies(id) === 1){
        document.cookie = "id" + id + "=" + "1";
        productInCart = parseInt(document.getElementById("add-tocart").innerHTML) + 1;
        totalPrice = parseInt(document.getElementById("total-price").innerHTML) + parseInt(price);
        document.getElementById("add-tocart").innerHTML = productInCart;
        document.getElementById("total-price").innerHTML = totalPrice;
        document.cookie = "productInCart=" + productInCart;
        document.cookie = "totalPrice=" + totalPrice;
        document.getElementById(id).onclick = null;
    }
}

function changeQuantityOfProduct(id, price) {
    product_id = id;
    console.log(product_id);
    document.cookie = "id" + product_id + "=" + document.getElementById("Qty" + product_id).value;
    productInCart = parseInt(document.getElementById("add-tocart").innerHTML) + parseInt(document.getElementById("Qty" + product_id).value);
    totalPrice = parseInt(document.getElementById("total-price").innerHTML) + parseInt(price) * parseInt(document.getElementById("Qty" + product_id).value);
    document.getElementById("add-tocart").innerHTML = productInCart;
    document.getElementById("total-price").innerHTML = totalPrice;
    document.cookie = "productInCart=" + productInCart;
    document.cookie = "totalPrice=" + totalPrice;
    document.getElementById(id).onclick = null;
    document.getElementById("Qty" + product_id).max = document.getElementById("Qty" + product_id).value;
    document.getElementById("Qty" + product_id).min = document.getElementById("Qty" + product_id).value;
    console.log(product_id);
}


function checkCookies(id) {
    
    var cookies = document.cookie.split(';').reduce((cookies, cookie) => {
        const [name, val] = cookie.split('=').map(c => c.trim());
        cookies[name] = val;
        return cookies;
    }, {});
    
  
    for (let cookieName in cookies) {
        if (parseInt(cookieName.substring(2)) === parseInt(id)){
            console.log("Done");
            result = 0;
        }
    }
    return result;
}
