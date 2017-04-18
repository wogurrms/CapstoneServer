/**
 * 
 */
function table(){
    $.ajax({
        url : "127.0.0.1:8080/Capstone/table",
        type: "get",
        success : function(responseData){
            for(var idx = 0; idx<responseData.customers.length;idx++){
            	$("#innertable").append("<td>"+responseData.customers[idx].id+"</td>"+"<td>"+responseData.customers[idx].date+"</td>"
            			+"<td>"+responseData.customers[idx].count+"</td>")
            }
        }
    });
}