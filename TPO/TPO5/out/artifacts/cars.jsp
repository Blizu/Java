<%@ page import="java.util.ArrayList" %><%-- Created by IntelliJ IDEA. --%>
<%@ page import="cars.Car" %><%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Wyszykiwanie samochodów</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<main>
    <setion style="display: flex; justify-content: space-around; align-items: center;">

        <table>
            <tr style="background-color: rgba(0,0,0,0)">
                <th>Rodzaj</th>
                <th>Producent</th>
                <th>Rok produkcji</th>
                <th>Kolor</th>
            </tr>

            <%
                ArrayList<Car> cars = (ArrayList<Car>) request.getAttribute("cars");

                for (Car car : cars) {%>
            <tr>
                <td><%=car.type%>
                </td>
                <td><%=car.manufacturer%>
                </td>
                <td><%=car.manufactureYear%>
                </td>
                <td><%=car.color%>
                </td>
            </tr>
            <%}%>

        </table>

        <aside>
            <h2>Wybierz jeden z poniższych filtrów</h2>

            <form action="/cars" method="GET">

                <label for="manufacturer">
                    Producent
                </label>
                <input id="manufacturer" type="text" name="manufacturer" value="<%=request.getAttribute("searchManufacturer")%>">
                <label for="type">
                    Rodzaj pojazdu
                </label>
                <input id="type" type="text" name="type" value="<%=request.getAttribute("searchType")%>">

                <label for="manufactureYear">
                   Rok produkcji
                </label>

                <select id="manufactureYear" name="manufactureYear">
                    <% if(request.getAttribute("searchManufacturerYear").equals("")){ %>
                    <option value="" selected>None</option>
                    <% }else{ %>
                    <option value="">None</option>
                    <%} %>

                    <%
                        int startDate = 1990;
                        int range = 30;
                        int stopDate = startDate + range;

                        for (int i = startDate; i < stopDate; i++) {%>

                    <% if(request.getAttribute("searchManufacturerYear").equals("" + i)){ %>
                    <option value="<%=i%>" selected><%=i%></option>
                    <% }else{ %>
                    <option value="<%=i%>"><%=i%></option>
                    <%} %>

                    <%}%>
                </select>

                <label for="color">
                    Kolor
                </label>
                <input id="color" type="text" name="color" value="<%=request.getAttribute("searchColor")%>">

                <div style="display: flex; justify-content: space-around; align-items: center;">

                    <button style="background: lightblue;" type="button" onclick="(() => {
                        const form = document.querySelector('form');

                        form.querySelector('select').selectedIndex = 0;

                        for(let element of form.querySelectorAll('input')) {
                            element.value = '';
                        }
                    })()">Wyczyść formularz</button>
                    <button style="background: lightblue;" type="submit">Szukaj</button>
                </div>
            </form>
        </aside>
    </setion>
</main>


</body>
</html>