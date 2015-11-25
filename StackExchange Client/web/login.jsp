<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page = "layout/header.jsp" flush = "true"/>
<jsp:useBean id="alert" type="String" scope="request" /> 

    <div class="inner-container">

        <div class="row">
            <div class="col-12">
                <% if (alert != null) { %>
                    <%= alert %>
                <% } %>
                <div class="login-form-wrapper">
                    <form id="askForm" action="${pageContext.request.contextPath}/auth" method="POST">
                        <div class="form-field">
                            <label for="email">Email</label>
                            <input name="email" id="email-login" class="login" type="text" placeholder="email@something.com" required>
                        </div>

                        <div class="form-field">
                            <label for="password">Password</label>
                            <input name="password" id="password-login" class="login" type="password" placeholder="password" required>
                        </div>
                        <input type="submit" id="" class="btn-submit" value="Log in">
                    </form>
                </div>
                
            </div> <!-- .col -->
        </div> <!-- .row -->
        
    </div> <!-- .inner-container -->

<jsp:include page = "layout/footer.jsp" flush = "true"/>

