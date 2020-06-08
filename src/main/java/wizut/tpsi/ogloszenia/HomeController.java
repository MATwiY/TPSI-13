package wizut.tpsi.ogloszenia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import wizut.tpsi.ogloszenia.jpa.*;
import wizut.tpsi.ogloszenia.services.OffersService;
import wizut.tpsi.ogloszenia.web.OfferFilter;

import javax.validation.Valid;
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
    public String home(Model model, OfferFilter offerFilter){

        int maxResult = 20;
        int firstResult = 0;

        List<Offer> offersSize = offersService.getOffers();
        List<CarManufacturer> carManufacturers = offersService.getCarManufacturers();
        List<FuelType> fuelTypes = offersService.getFuelType();
        List<Offer> offers = offersService.getOffers(offerFilter, firstResult);

        if(offerFilter.getManufacturerId() != null) {
            List<CarModel> carModels = offersService.getCarModels(offerFilter.getManufacturerId());
            model.addAttribute("carModels", carModels);
        }
        int pages = (int) Math.ceil((float)offersSize.size()/maxResult);

        model.addAttribute("page", firstResult);
        model.addAttribute("pages", pages);
        model.addAttribute("fuelTypes", fuelTypes);
        model.addAttribute("carManufacturers", carManufacturers);
        model.addAttribute("offers", offers);

        return "offersList";
    }

    @GetMapping("/offerList/{page}/{pages}")
    public String home(Model model, @PathVariable("page") Integer page, @PathVariable("pages") Integer pages, OfferFilter offerFilter){
        List<CarManufacturer> carManufacturers = offersService.getCarManufacturers();
        List<FuelType> fuelTypes = offersService.getFuelType();
        List<Offer> offers = offersService.getOffers(offerFilter, page);

        if(offerFilter.getManufacturerId() != null) {
            List<CarModel> carModels = offersService.getCarModels(offerFilter.getManufacturerId());
            model.addAttribute("carModels", carModels);
        }
        model.getAttribute("pages");

        model.addAttribute("pages", pages);
        model.addAttribute("page", page);
        model.addAttribute("fuelTypes", fuelTypes);
        model.addAttribute("carManufacturers", carManufacturers);
        model.addAttribute("offers", offers);

        return "offersList";
    }

    @GetMapping("/offer/{id}")
    public String offerDetails(Model model, @PathVariable("id") Integer id){

        Offer offer = offersService.getOffer(id);
        model.addAttribute("offer", offer);

        return "offerDetail";
    }

    @GetMapping("/newoffer")
    public String newOfferForm(Model model, Offer offer){
        List<CarModel> carModels = offersService.getCarModels();
        List<BodyStyle> bodyStyles = offersService.getBodyStyle();
        List<FuelType> fuelTypes = offersService.getFuelType();

        model.addAttribute("carModels", carModels);
        model.addAttribute("bodyStyles", bodyStyles);
        model.addAttribute("fuelTypes", fuelTypes);

        model.addAttribute("header", "Nowe ogłoszenie");
        model.addAttribute("action", "/newoffer");

        return "offerForm";
    }

    @PostMapping("/newoffer")
    public String saveNewOffer(Model model, @Valid Offer offer, BindingResult binding){

        if(binding.hasErrors()){
            List<CarModel> carModels = offersService.getCarModels();
            List<BodyStyle> bodyStyles = offersService.getBodyStyle();
            List<FuelType> fuelTypes = offersService.getFuelType();

            model.addAttribute("carModels", carModels);
            model.addAttribute("bodyStyles", bodyStyles);
            model.addAttribute("fuelTypes", fuelTypes);

            return "offerForm";

        }else{
            offer = offersService.createOffer(offer);

            return "redirect:/offer/" + offer.getId();
        }
    }

    @GetMapping("/deleteoffer/{id}")
    public String deleteOffer(Model model, @PathVariable("id") Integer id){
        Offer offer = offersService.delteOffer(id);

        model.addAttribute("offer", offer);
        return "deleteOffer";
    }

    @GetMapping("/editoffer/{id}")
    public String editOffer(Model model, @PathVariable("id") Integer id){

        model.addAttribute("header", "Edycja ogłoszenia");
        model.addAttribute("action", "/editoffer/" + id);

        Offer offer = offersService.getOffer(id);
        model.addAttribute("offer", offer);

        List<CarModel> carModels = offersService.getCarModels();
        List<BodyStyle> bodyStyles = offersService.getBodyStyle();
        List<FuelType> fuelTypes = offersService.getFuelType();

        model.addAttribute("carModels", carModels);
        model.addAttribute("bodyStyles", bodyStyles);
        model.addAttribute("fuelTypes", fuelTypes);

        return "offerForm";
    }

    @PostMapping("/editoffer/{id}")
    public String saveEditOffer(Model model, @PathVariable("id") Integer id, @Valid Offer offer, BindingResult binding){
        if(binding.hasErrors()){
            model.addAttribute("header", "Edycja ogłoszenia");
            model.addAttribute("action", "/editoffer/" + id);

            List<CarModel> carModels = offersService.getCarModels();
            List<BodyStyle> bodyStyles = offersService.getBodyStyle();
            List<FuelType> fuelTypes = offersService.getFuelType();

            model.addAttribute("carModels", carModels);
            model.addAttribute("bodyStyles", bodyStyles);
            model.addAttribute("fuelTypes", fuelTypes);

            return "offerForm";
        }
        offersService.saveOffer(offer);

        return "redirect:/offer/" + offer.getId();
    }

}
