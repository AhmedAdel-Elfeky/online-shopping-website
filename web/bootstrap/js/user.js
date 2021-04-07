/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
let setId = 0;
function deleteUser(id)
{

    document.getElementById("row" + id).remove();
    document.cookie = "userId" + id + "=" + id;


}

function updateUserLimit(id) {
    setId = id;

}

function Update() {
    var creditLimit = document.getElementById("inputEmail").value;
    document.cookie = "updateUserIdLimit" + setId + "=" + creditLimit;
}