/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function deleteUser(id)
{

        document.getElementById("row"+id).remove();
        document.cookie = "userId"+id +"="+id;

    
}

function updateUserLimit(id){
  document.cookie = "updateUserIdLimit"+id +"="+id;
}