package wizut.tpsi.ogloszenia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import wizut.tpsi.ogloszenia.jpa.*;
import wizut.tpsi.ogloszenia.services.OffersService;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private OffersService offersService;

    // Kod do lab11 FURKA4U cz1
    @RequestMapping("/home")
    public String home1(Model model){
        CarManufacturer carManufacturer = offersService.getCarManufacturer(2);
        CarModel carModel = offersService.getCarModel(3);

        model.addAttribute("model_id", carModel.getId());
        model.addAttribute("model_name", carModel.getName());
        model.addAttribute("model_maunf", carModel.getManufacturer().getName());

        model.addAttribute("manufacturer_id", carManufacturer.getId());
        model.addAttribute("manufacturer_name", carManufacturer.getName());

        return "home";
    }

    @GetMapping("/")
    public String home(Model model){
        List<CarManufacturer> carManufacturers = offersService.getCarManufacturers();
        List<CarModel> carModels = offersService.getCarModels();
        List<Offer> offers = offersService.getOffers();

        model.addAttribute("carManufacturers", carManufacturers);
        model.addAttribute("carModels", carModels);
        model.addAttribute("offers", offers);

        return "offersList";
    }

    @GetMapping("/offer/{id}")
    public String oggerDetails(Model model, @PathVariable("id") Integer id){

        Offer offer = offersService.getOffer(id);
        model.addAttribute("offer", offer);

        return "offerDetail";
    }

}
