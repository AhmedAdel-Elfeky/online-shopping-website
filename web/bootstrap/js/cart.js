let totalOrderPrice = 0;

reloadOrder();
updateCartinfo();

function incrementQuantity(id) {
    product_id = id.substring(4);
    if (document.getElementById("quantity" + product_id).value < document.getElementById("quantity" + product_id).getAttribute("data-max-qunatity")) {
        document.getElementById("quantity" + product_id).value = parseInt(document.getElementById("quantity" + product_id).value) + 1;
        document.getElementById("quantity-total" + product_id).innerText = parseInt(document.getElementById("price" + product_id).innerText) * parseInt(document.getElementById("quantity" + product_id).value);
        document.cookie = "id" + product_id + "=" + document.getElementById("quantity" + product_id).value;
//    document.cookie=product_id+"="+document.getElementById("quantity-total" + product_id).innerText;
        updateCartinfo();
    }
}

function decrementQuantity(id)
{
    product_id = id.substring(3);
    if (document.getElementById("quantity" + product_id).value > 1) {
        document.getElementById("quantity" + product_id).value = parseInt(document.getElementById("quantity" + product_id).value) - 1;
        document.getElementById("quantity-total" + product_id).innerText = parseInt(document.getElementById("price" + product_id).innerText) * parseInt(document.getElementById("quantity" + product_id).value);

        document.cookie = "id" + product_id + "=" + document.getElementById("quantity" + product_id).value;
//    document.cookie=product_id+"="+document.getElementById("quantity-total" + product_id).innerText;
        updateCartinfo();
    }
}

function deleteProduct(id)
{
    product_id = id.substring(6);
    document.getElementById("row" + product_id).remove();
//    document.cookie = "id" + product_id + "=";
    updateCartinfo(totalOrderPrice);
    document.cookie =  "id" + product_id + "=" +"0";

}

function updateCartinfo() {
    let orderPrice = document.getElementsByClassName("product-total");
    totalOrderPrice = 0;
    for (let i = 0; i < orderPrice.length; i++) {
        totalOrderPrice += parseInt(orderPrice[i].innerText);
    }
    document.getElementById("total-order-price1").innerText = totalOrderPrice;
    document.getElementById("total-order-price2").innerText = totalOrderPrice;

    let cartQuantity = document.getElementsByClassName("span1");
    productInCart = 0;
    for (let i = 0; i < cartQuantity.length; i++) {
        productInCart += parseInt(cartQuantity[i].value);
    }
    document.getElementById("add-tocart").innerHTML = productInCart;
    document.getElementById("total-price").innerHTML = totalOrderPrice;
    document.cookie = "productInCart=" + productInCart;
    document.cookie = "totalPrice=" + totalOrderPrice;
    // document.cookie = "cartChanged=true";
}

function changeQuantity(id)
{
    product_id = id.substring(8);
    if (document.getElementById("quantity" + product_id).value <= document.getElementById("quantity" + product_id).getAttribute("data-max-qunatity")) {
        document.getElementById("quantity-total" + product_id).innerText = parseInt(document.getElementById("price" + product_id).innerText) * parseInt(document.getElementById("quantity" + product_id).value);
        document.cookie = "id" + product_id + "=" + document.getElementById("quantity" + product_id).value;
        updateCartinfo();
    }
}

