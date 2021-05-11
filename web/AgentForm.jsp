<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=, initial-scale=1.0">


    <!-- BootStrap css -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">

    <title>Agency</title>
</head>
<body>
<div class="row">
    <div class="col-sm-4">

        <div class="container">

            <h3>AGENT FORM</h3>
            <form>
                    <label for="name">National ID</label>
                    <input type="text" class="form-control" id="nid" name="nid" placeholder="National ID" pattern="^119\d{13}$" required="" title="respect national id format">
                
                    <label for="name">name</label>
                    <input type="text" class="form-control" id="name" name="name" placeholder="Family name" pattern="^[a-zA-Z]*$" required="" title="name must be only characters">
              
                    <label for="surname">Surname</label>
                    <input type="text" class="form-control" id="surname" name="surname" placeholder="Firstname" pattern="^[a-zA-Z]*$" required="" title="name must be only characters">
                 
                    <label for="phone">Phone Number</label>
                    <input type="text" class="form-control" id="phone" name="phone" placeholder="phone number" pattern="^07[3,8,2]?\d{7}$" required="" title="phone number format 0780000000 or 0720000000 0730000000">
                  
                    <label for="street">Street</label>
                    <input type="text" class="form-control" id="street" name="street" placeholder="KK314st" pattern="^[A-Z]{2}[1-9]{3}[a-z]{2}$" required="" title="phone number format 0780000000 or 0720000000 0730000000">
                  
                    <label for="username">Username</label>
                    <div class="input-group">
                      <div class="input-group-prepend">
                        <span class="input-group-text" id="usernameat">@</span>
                      </div>
                      <input type="text" class="form-control" id="username" name="username" placeholder="Username" aria-describedby="inputGroupPrepend2" pattern="^[a-z]{6,15}$" required="">
                    </div>
                 
                    <label for="password">Password</label>
                    <input type="text" class="form-control" id="password" name="password" placeholder="Password" pattern="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$" required="">
                  
                  <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="invalidCheck2" required>
                    <label class="form-check-label" for="invalidCheck2">
                      Agree to terms and conditions
                    </label>
                  </div>
                <button class="btn btn-primary" type="submit">Save</button>
              </form>
        </div>
    </div>
    <div class="col-sm-8">

        <h3>AGENTs TABLE</h3>

        <div class="panel-body">
            <table id="tbl-agent" class="table table-bordered" cellpadding="0" cellspacing="0" width="100%">
              <thead>
                <tr>
                  <th></th>
                  <th></th>
                  <th></th>
                  <th></th>
                  <th></th>
                  <th></th>
                </tr>
              </thead>
            </table>
        </div>

    </div>
</div>

    
    
    <!-- bootstrap js -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

    <!-- jquery componets -->
    
    <script src="component/jquery/jquery.js" type="text/javascript"></script>
    <script src="component/jquery/jquery.min.js" type="text/javascript"></script>
    <script src="component/jquery.validate.min.js" type="text/javascript"></script>
</body>
</html>