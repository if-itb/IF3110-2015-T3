<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page = "layout/header.jsp" flush = "true"/>

    <div class="inner-container">

        <div class="row">
            <div class="col-12">

                <div class="register-form-wrapper">
                    <form id="askForm" action="newuser" method="POST">
                        <div class="form-field">
                            <label for="name">Name</label>
                            <input id="name-register" class="login" type="text" name="nama" placeholder="name" required>
                        </div>

                        <div class="form-field">
                            <label for="email-register">Email</label>
                            <input id="email-register" class="login" type="text" name="email" placeholder="email@something.com" required>
                        </div>

                        <div class="form-field">
                            <label for="password-regsiter">Password</label>
                            <input id="password-register" class="login" type="password" name="password" placeholder="password" required>
                        </div>
                        <input type="submit" id="" class="btn-submit" value="Sign up">
                    </form>
                </div>
                
            </div> <!-- .col -->
        </div> <!-- .row -->
        
    </div> <!-- .inner-container -->
    
<jsp:include page = "layout/footer.jsp" flush = "true"/>

