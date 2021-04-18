/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function(){ 
    $('#commentBtn').click(function(){ 
	   var comment = $('#comment').val(); 
	     $.ajax({ 
		         type:'POST',
		         data:{comment:comment},
			 url:'ReviewController',
			success: function(result){
				$("#result").html(result);
                                $("#comment").val("\n Enter Your Comment");
			}
		});
	});
});