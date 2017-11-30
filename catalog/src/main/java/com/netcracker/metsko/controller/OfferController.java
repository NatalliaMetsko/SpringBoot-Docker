package com.netcracker.metsko.controller;

import com.netcracker.metsko.entity.Category;
import com.netcracker.metsko.entity.Offer;
import com.netcracker.metsko.entity.Price;
import com.netcracker.metsko.entity.Tag;
import com.netcracker.metsko.service.OfferService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/catalog/offers")
public class OfferController {

    @Autowired
    OfferService offerService;

    @PostMapping
    public ResponseEntity<Offer> createOffer(@RequestBody Offer newOffer) throws NotCreatedException, SQLException {
        offerService.createOffer(newOffer);
        return  new ResponseEntity<Offer>(newOffer, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Offer> updateOffer(@RequestBody Offer offer) throws NotUpdatedException, SQLException {
        Offer updatedOffer = offerService.updateOffer(offer);
        return new ResponseEntity<Offer>(updatedOffer, HttpStatus.OK);
    }

    @DeleteMapping( value = "/{id}")
    public ResponseEntity<Long> deleteOffer(@PathVariable("id") Long id) throws NotDeletedException, SQLException {
        offerService.deleteOffer(id);
        return  new ResponseEntity<Long>(id, HttpStatus.OK);
    }

    @PostMapping( value = "/{id}")
    public ResponseEntity<Long> setAvailability(@PathVariable("id") Long id, @RequestBody boolean availability) throws NotUpdatedException, SQLException {
        offerService.setAvailability(id, availability);
        return new ResponseEntity<Long>(id, HttpStatus.OK);
    }

    @GetMapping( value="/{id}")
    public ResponseEntity<Offer> findById(@PathVariable("id") Long id) throws NotFoundException,SQLException {
        Offer foundOffer = offerService.findById(id);
        return new ResponseEntity<Offer>(foundOffer, HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Offer>> findAll() throws NotFoundException, SQLException {
         List <Offer> offerList =offerService.findAll();
         return new ResponseEntity<List<Offer>>(offerList,HttpStatus.FOUND);
    }

    @GetMapping( value = "/bytag/")
    public List<Offer> findByTags(@RequestBody List<Tag> tagList) throws NotFoundException, SQLException {
        return offerService.findByTags(tagList);
    }

    @GetMapping( value = "/available/")
    public ResponseEntity<List<Offer>> findAvailableOffers() throws NotFoundException, SQLException {
        List<Offer> offerList = offerService.findAvailableOffers();
        return new ResponseEntity<List<Offer>>(offerList, HttpStatus.FOUND);
    }

    @PostMapping( value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> addPrice(@PathVariable("id") Long id, @RequestBody Price price) throws NotUpdatedException,SQLException {
        offerService.addPrice(id, price);
        return new ResponseEntity<Long>(id, HttpStatus.OK);
    }

    @PostMapping( value = "/{id}")
    public ResponseEntity<Long> changePrice(@PathVariable("id") Long id, @RequestBody Price price) throws NotUpdatedException, SQLException {
        offerService.changePrice(id, price);
        return new ResponseEntity<Long>(id, HttpStatus.OK);
    }

    @GetMapping( value = "/pricefilter/")
    public ResponseEntity<List<Offer>> getPriceFromTo(@RequestBody Price priceFrom, @RequestBody Price priceTo) throws NotFoundException, SQLException {
        List<Offer> offerList=  offerService.getPriceFromTo(priceFrom, priceTo);
        return new ResponseEntity<List<Offer>>(offerList,HttpStatus.FOUND);
    }

    @PostMapping( value = "/{id}")
    public ResponseEntity<Long> addTag(@PathVariable("id") Long id, @RequestBody Tag tag) throws NotUpdatedException,SQLException {
        offerService.addTag(id, tag);
        return new ResponseEntity<Long>(id ,HttpStatus.OK);
    }

    @DeleteMapping( value = "/{id}")
    public ResponseEntity<Long> removeTag(@PathVariable("id") Long id, @RequestBody Tag tag) throws NotUpdatedException,SQLException {
        offerService.removeTag(id, tag);
        return new ResponseEntity<Long>(id, HttpStatus.OK);
    }

    @PostMapping( value = "/{id}")
    public ResponseEntity<Long> addCategory(@PathVariable("id") Long id, @RequestBody Category category) throws NotUpdatedException,SQLException {
        offerService.addCategory(id, category);
        return new ResponseEntity<Long>(id,HttpStatus.OK);
    }

    @DeleteMapping( value = "/{id}")
    public ResponseEntity<Long> removeCategory(@PathVariable("id") Long id) throws NotDeletedException,SQLException {
        offerService.removeCategory(id);
        return new ResponseEntity<Long>(id, HttpStatus.OK);
    }

}
