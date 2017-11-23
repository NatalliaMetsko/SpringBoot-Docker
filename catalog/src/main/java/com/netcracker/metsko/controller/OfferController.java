package com.netcracker.metsko.controller;

import com.netcracker.metsko.entity.Category;
import com.netcracker.metsko.entity.Offer;
import com.netcracker.metsko.entity.Price;
import com.netcracker.metsko.entity.Tag;
import com.netcracker.metsko.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/catalog/offers")
public class OfferController {

    @Autowired
    OfferService offerService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createOffer(@RequestBody Offer offer) throws SQLException {
        offerService.createOffer(offer);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/")
    @ResponseStatus(HttpStatus.OK)
    public Offer updateOffer(@RequestBody Offer offer) throws SQLException {
        return  offerService.updateOffer(offer);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOffer(@PathVariable("id") Long id) throws SQLException {
        offerService.deleteOffer(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void setAvailability(@PathVariable("id") Long id, @RequestBody boolean availability) throws SQLException {
        offerService.setAvailability(id, availability);
    }

    @RequestMapping(method = RequestMethod.GET, value="/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Offer findById(@PathVariable("id") Long id) throws SQLException {
        return  offerService.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Offer> findAll() throws SQLException {
        return  offerService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/bytag/")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Offer> findByTags(@RequestBody List<Tag> tagList) throws SQLException {
        return offerService.findByTags(tagList);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/available/")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Offer> findAvailableOffers() throws SQLException {
        return offerService.findAvailableOffers();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void addPrice(@PathVariable("id") Long id, @RequestBody Price price) throws SQLException {
        offerService.addPrice(id, price);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void changePrice(@PathVariable("id") Long id, @RequestBody Price price) throws SQLException {
        offerService.changePrice(id, price);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/pricefilter/")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Offer> getPriceFromTo(@RequestBody Price priceFrom, @RequestBody Price priceTo) throws SQLException {
        return  offerService.getPriceFromTo(priceFrom, priceTo);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void addTag(@PathVariable("id") Long id, @RequestBody Tag tag) throws SQLException {
        offerService.addTag(id, tag);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeTag(@PathVariable("id") Long id, @RequestBody Tag tag) throws SQLException {
        offerService.removeTag(id, tag);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void addCategory(@PathVariable("id") Long id, @RequestBody Category category) throws SQLException {
        offerService.addCategory(id, category);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeCategory(@PathVariable("id") Long id) throws SQLException {
        offerService.removeCategory(id);
    }

}
