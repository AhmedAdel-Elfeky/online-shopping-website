//Bootsshop-----------------------//
$(document).ready(function () {
    /* carousel of home page animation */
    $('#myCarousel').carousel({
        interval: 4000
    })
    $('#featured').carousel({
        interval: 4000
    })
    $(function () {
        $('#gallery a').lightBox();
    });

    $('.subMenu > a').click(function (e)
    {
        e.preventDefault();
        var subMenu = $(this).siblings('ul');
        var li = $(this).parents('li');
        var subMenus = $('#sidebar li.subMenu ul');
        var subMenus_parents = $('#sidebar li.subMenu');
        if (li.hasClass('open'))
        {
            if (($(window).width() > 768) || ($(window).width() < 479)) {
                subMenu.slideUp();
            } else {
                subMenu.fadeOut(250);
            }
            li.removeClass('open');
        } else
        {
            if (($(window).width() > 768) || ($(window).width() < 479)) {
                subMenus.slideUp();
                subMenu.slideDown();
            } else {
                subMenus.fadeOut(250);
                subMenu.fadeIn(250);
            }
            subMenus_parents.removeClass('open');
            li.addClass('open');
        }
    });
    var ul = $('#sidebar > ul');
    $('#sidebar > a').click(function (e)
    {
        e.preventDefault();
        var sidebar = $('#sidebar');
        if (sidebar.hasClass('open'))
        {
            sidebar.removeClass('open');
            ul.slideUp(250);
        } else
        {
            sidebar.addClass('open');
            ul.slideDown(250);
        }
    });

});
var price = document.getElementById("price");
var quantity = document.getElementById("appendedInputButtons");
function increment() {

    quantity.value++;
    price.innerHTML = parseInt(price.innerHTML) + 120;

}
function decrement() {


    quantity.value--;
    price.innerHTML = parseInt(price.innerHTML) - 120;
    if (quantity.value == 0)
    {
        quantity.value = 1;
        price.innerHTML = 120;
    }
}


function getCookie(cname) {
  var name = cname + "=";
  var decodedCookie = decodeURIComponent(document.cookie);
  var ca = decodedCookie.split(';');
  for(var i = 0; i <ca.length; i++) {
    var c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}
function checkCookie() {
    var userExistance = getCookie(userExist);
    console.log(userExistance);
    if (userExistance === "true") {
        document.getElementById("registerationForm").addEventListener("submit", function (event) {
            console.log(userExistance);
            event.preventDefault()
        });

    }

}
checkCookie(userExist);