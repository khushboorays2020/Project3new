<!DOCTYPE html>
<html>
<head>
	<title></title>
	<style type="text/css"></style>
	    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="js/validate-bootstrap/validate-bootstrap.jquery.min.js"></script>
    <script>
    $(function() {
        $('form').validator({
            validHandlers: {
                '.customhandler':function(input) {
                    //may do some formatting before validating
                    input.val(input.val().toUpperCase());
                    //return true if valid
                    return input.val() === 'JQUERY' ? true : false;
                }
            }
        });

        $('form').submit(function(e) {
            e.preventDefault();

            if ($('form').validator('check') < 1) {
                alert('Hurray, your information will be saved!');
            }
        })
    })
    </script>
	
</head>
<body>
    <div class="container">
        <h1>A demo of Bootstrap validate form</h1>
    </div>
    <!-- don't forget novalidate to stop browser form validation -->
    <form class="form">
    <div class="container">
        <div class="row">
            <div class='col-sm-4 form-group'>
                <label for="name">Your Name:</label>
                <input id="lname" class="form-control" min="3" required type="text" data-error-msg="Must enter your name?">
            </div>
            <div class='col-sm-4 form-group'>
                <label for="name">Email:</label>
                <input id="email" class="form-control" type="email" required data-error-msg="The email is required in valid format!">
            </div>
        </div>
    </div>
    </form>
</body>
</html>