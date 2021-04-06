
var error = document.getElementsByClassName("addproduct_errors");
var mobil_radiabtn = document.getElementById("mobil_radiabtn");
var laptop_radiabtn = document.getElementById("laptop_radiabtn");
var pname = document.getElementById("input_productname");
var pprice = document.getElementById("input_pprice");
var pquantity = document.getElementById("input_pquantity");
var purl = document.getElementById("input_purl");
var pufeatured = document.getElementById("input_pfeatured");
var pufeatured = document.getElementById("input_pufeatured");
var pdesc = document.getElementById("input_pdesc");
var pdesc = document.getElementById("input_pdesc");
var pbrand = document.getElementById("input_pbrand");
var preleased = document.getElementById("input_preleased");
var ppkg = document.getElementById("input_ppackage");
var pdisplay = document.getElementById("input_pdisplay");
var pfeature = document.getElementById("input_pFeatures");
var pdate = document.getElementById("input_pdate");


var form = document.getElmentById("addproductForm");
var editStatus = document.getElmentById("editResult");




function validateMyForm()
{

    if (pprice.value == "" || pname.value == "" || pdate.value == "" || pquantity.value == ""
            || purl.value == "" || pdesc.value == "" || pbrand.value == "" || preleased.value == "" || ppkg.value == ""
            || pdisplay.value == "" || pfeature.value == "")
    {
//         editStatus.style.visibility = "hidden";
         if (pprice.value == "")
        {
            error[2].style.visibility = "visible";
        } else
        {
            error[2].style.visibility = "hidden";
        }

        if (pname.value == "")
        {
            error[0].style.visibility = "visible";
        } else
        {
            error[0].style.visibility = "hidden";
        }

        if (pdate.value == "")
        {
            error[1].style.visibility = "visible";
        } else
        {
            error[1].style.visibility = "hidden";
        }

        if (pquantity.value == "")
        {
            error[3].style.visibility = "visible";
        } else
        {
            error[3].style.visibility = "hidden";
        }

        if (purl.value == "")
        {
            error[4].style.visibility = "visible";
        } else
        {
            error[4].style.visibility = "hidden";
        }
        if (pbrand.value === "")
        {
            error[6].style.visibility = "visible";
        } else
        {
            error[6].style.visibility = "hidden";
        }
        if (pdesc.value == "")
        {
            error[5].style.visibility = "visible";
        } else
        {
            error[5].style.visibility = "hidden";
        }

        

        if (preleased.value == "")
        {
            error[7].style.visibility = "visible";
        } else
        {
            error[7].style.visibility = "hidden";
            
        }

        if (ppkg.value == "")
        {
            error[8].style.visibility = "visible";
        } else
        {
            error[8].style.visibility = "hidden";
        }

        if (pdisplay.value == "")
        {
            error[9].style.visibility = "visible";
        } else
        {
            error[9].style.visibility = "hidden";
        }

        if (pfeature.value == "")
        {
            error[10].style.visibility = "visible";
        } else
        {
            error[10].style.visibility = "hidden";
        }
       
        return false;
    }

    for (var i = 0; i < 11; i++)
    {
        error[i].style.visibility = "hidden";
    }

    return true;
}

savebtn.addEventListener("click",validateMyForm);