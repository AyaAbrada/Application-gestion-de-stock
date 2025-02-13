<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

     <head>
            <title>Application gestion de stock</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
     </head>

     <body>

         <header>
             <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                 <div>
                     <a href="https://www.javaguides.net" class="navbar-brand"> Application gestion du stock </a>
                 </div>

                 <ul class="navbar-nav">
                     <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Produit</a></li>
                 </ul>
             </nav>
         </header>
         <br>
         <div class="container col-md-5">
             <div class="card">
                 <div class="card-body">
                     <c:if test="${produit != null}">
                         <form action="update" method="post">
                     </c:if>
                     <c:if test="${produit == null}">
                         <form action="insert" method="post">
                     </c:if>

                     <caption>
                         <h2>
                               
                             <c:if test="${produit == null}">
                                 Ajouter un produit
                             </c:if>
                         </h2>
                     </caption>

                     <c:if test="${produit != null}">
                         <input type="hidden" name="id" value="<c:out value='produit.id}' />" />
                     </c:if>

                     <fieldset class="form-group">
                            <label>Nom du produit</label> <input type="text" value="<c:out value='${produit.name}' />" class="form-control" name="name" required="required">
                     </fieldset>

                     <fieldset class="form-group">
                            <label>Description</label> <input type="text" value="<c:out value='${produit.description}' />" class="form-control" name="description">
                     </fieldset>

                     <fieldset class="form-group">
                            <label>Quantité en stock</label> <input type="text" value="<c:out value='${produit.quantite}' />" class="form-control" name="quantite">
                     </fieldset>
                        
                     <fieldset class="form-group">
                            <label>Prix unitaire :</label> <input type="text" value="<c:out value='${produit.prix}' />" class="form-control" name="prix">
                     </fieldset>
                        
                     <fieldset class="form-group">
                            <label>Catégorie du produit : </label> <input type="text" value="<c:out value='${produit.categorie}' />" class="form-control" name="categorie">
                     </fieldset>

                     <button type="submit" class="btn btn-success">Ajouter</button>
                        </form>
                 </div>
             </div>
         </div>
     </body>

     </html>