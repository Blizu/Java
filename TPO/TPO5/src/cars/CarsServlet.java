package cars;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

@WebServlet("/cars")
public class CarsServlet extends HttpServlet {
    private ArrayList<Car> carArrayList = new ArrayList<Car>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (carArrayList.isEmpty()) {
            ServletContext context = getServletContext();

            //wskazuje ścieżkę do pliku
            File carsData = new File(context.getRealPath("carsFile.csv"));

            Scanner scanner = new Scanner(carsData, "UTF-8");

            while (scanner.hasNextLine()) {

                String carData = scanner.nextLine();

                String[] parsedCarData = carData.split(",");

                String type = parsedCarData[0];
                String manufacturer = parsedCarData[1];
                int manufactureYear = Integer.parseInt(parsedCarData[2]);
                String color = parsedCarData[3];

                Car car = new Car(type, manufacturer, manufactureYear, color);

                carArrayList.add(car);
            }

            scanner.close();
        }

        String searchType = Optional.ofNullable(request.getParameter("type")).orElse("");
        String searchManufacturer = Optional.ofNullable(request.getParameter("manufacturer")).orElse("");
        String searchManufacturerYear = Optional.ofNullable(request.getParameter("manufactureYear")).orElse("");
        String searchColor = Optional.ofNullable(request.getParameter("color")).orElse("");

        ArrayList<Car> searchedCars = (ArrayList<Car>)this.carArrayList.clone();

        if(!searchType.isEmpty()) {
            System.out.println(searchType);
            searchedCars.removeIf((car) -> !car.type.equals(searchType));
        }

        if(!searchManufacturer.isEmpty()) {
            System.out.println(searchManufacturer);
            searchedCars.removeIf((car) -> !car.manufacturer.equals(searchManufacturer));
        }

        if(!searchManufacturerYear.isEmpty()) {
            System.out.println(searchManufacturerYear);

            try {
                int year = Integer.parseInt(searchManufacturerYear);
                searchedCars.removeIf((car) -> car.manufactureYear != year);
            } catch (NumberFormatException exception) {
                System.out.println("Nie można sparsować roku produkcji: " + searchManufacturerYear);
            }
        }

        if(!searchColor.isEmpty()) {
            System.out.println(searchColor);
            searchedCars.removeIf((car) -> !car.color.equals(searchColor));
        }

        request.setAttribute("cars", searchedCars);
        request.setAttribute("searchType", searchType);
        request.setAttribute("searchManufacturer", searchManufacturer);
        request.setAttribute("searchManufacturerYear", searchManufacturerYear);
        request.setAttribute("searchColor", searchColor);

        RequestDispatcher dispatcher = request.getRequestDispatcher("cars.jsp");
        dispatcher.forward(request, response);
    }
}