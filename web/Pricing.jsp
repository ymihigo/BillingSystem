<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- BootStrap css -->

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
    
    <!--data table-->
    
    <link href="//cdn.datatables.net/1.10.24/css/jquery.dataTables.min.css" rel="stylesheet">
    
    <title>Pricing</title>
</head>
<body>
    Current Price: <div id="currentPrice"></div><br>
    Updated On: <div id="updateDate"></div><br>

    <form id="price-frm">
        <div class="form-group row">
            <label for="colFormLabelLg" class="col-sm-2 col-form-label col-form-label-lg">Price Amount</label>
            <div class="col-sm-2">
              <input type="text" id="price" name="price" class="form-control form-control-lg"  placeholder="amount/cubic meter" size="30px" required="" pattern="^[0-9](\d*\.)?\d+$" title="only positive numbers are allowed">
            </div>
            <div class="col-sm-2">
              <button type="submit" class="btn btn-lg btn-outline-success" onclick="addPrice()">Submit</button>
              </div>
          </div>
    </form>
    <br>
    <div id="response">

    </div>
<br><br>

    <h3>PRICING TABLE</h3>

    <div class="panel-body">
        <table id="tbl-price" class="table table-bordered" cellpadding="0" cellspacing="0" width="100%">
          <thead>
            <tr>
              <th></th>
              <th></th>
              <th></th>
              <th></th>
            </tr>
          </thead>
        </table>
    </div>


    <!-- bootstrap js -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

    <!-- jquery componets -->
    
    <script src="component/jquery/jquery.js" type="text/javascript"></script>
    <script src="component/jquery/jquery.min.js" type="text/javascript"></script>
    <script src="component/jquery.validate.min.js" type="text/javascript"></script>

    <!--data table-->
    
    <script src="//cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js" type="text/javascript"></script>
    
    <script>
        getAll();
        currentPrice();
        var isNew=true;

        function addPrice(){

            if($("#price-frm").valid()){
                var url="";
                var data="";
                var method;

             if(isNew === true){
                 url='AdminPanel?a=newPrice';
                 data = $("#price-frm").serialize();
                method = 'POST';
            }
//            else{
//                url='update.jsp';
//                data = $("#frmstudent").serialize();
//                method = 'POST';
//            }

          $.ajax({
            type : method,
            url : url,
            dataType : 'JSON',
            data : data,

//          success : function(data){
//            getAll();
//            if(isNew === true){
//              
//              alert("student added");
//            }
//        }


             success: function(data){
//             allPrice();
//             alert(data);
//             console.log(data);
//             currentPrice();
            getAll();
            currentPrice();

            alert(data);
              },
            error: function(jqXHR, textStatus, message) {
//                 error handling
//                 allPrice();
                getAll();
                currentPrice();
               alert(jqXHR.responseText);
                },
            complete: function(){
              window.location.reload(true);
              } 

    });
}

}

      function getAll(){
            $('#tbl-price').dataTable().fnDestroy();
            $.ajax({
              url : "AdminPanel?a=allPrices",
              type : 'GET',
              dataType: 'JSON',
              
              success: function (data) {
                        $('#tbl-price').dataTable({
                          "aaData":data,
                          "scrollX":true,
                          "aoColumns":
                            
                            [
                              {"sTitle": "Amount","mData": "amount"},
                              {"sTitle": "Updated on","mData": "updatedDate"},
                              {"sTitle": "is it Active","mData": "status"},
                              {
                                "sTitle": "Edit", "mData": "id",
                                "render" :function (mData)
                                {
                                  return '<button class="btn btn-success" onclick="get_Details('+mData+')">View</button>';
                                }
                              }
                              
                            ]
                            
                          
                        });
                    }
            });
          }
          
          function get_Details(id){
            $.ajax({
              url:"AdminPanel?a=View_Price_Detail",
              type: "POST",
              data : {"id":id},
              
              success: function (data, textStatus, jqXHR) {
                
                        if(isNew === true){
                          var obj=JSON.parse(data);
                          $('#currentPrice').html(obj[0].price);
                          $('#updateDate').html(obj[0].date);
                          }
                    },
              error: function (jqXHR, textStatus, errorThrown) {
                        console.log(errorThrown);
                        alert(jqXHR.responseText);
                    },
              complete: function (jqXHR, textStatus ) {
                        
                }
            });
          }
          
          function currentPrice(){
            $.ajax({
              url: "AdminPanel?a=current_price",
              type: "GET",
              datatype: "JSON",
              
              success:function(data){
                if(isNew === true){
                  var obj=JSON.parse(data);
                  $('#currentPrice').html(obj[0].price);
                  $('#updateDate').html(obj[0].date);
                }
              }
              
            });
          }
    </script>

</body>
</html>