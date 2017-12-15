package com.netcracker.metsko.controller;

import com.netcracker.metsko.entity.*;
import com.netcracker.metsko.exceptions.NotCreatedException;
import com.netcracker.metsko.exceptions.NotDeletedException;
import com.netcracker.metsko.exceptions.NotFoundException;
import com.netcracker.metsko.exceptions.NotUpdatedException;
import com.netcracker.metsko.service.OfferService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/catalog/offers")
@Api(value = "Controller", description = "This is offer controller")
public class OfferController {

    @Autowired
    OfferService offerService;

    @PostMapping
    @ApiOperation(httpMethod = "POST",
            value = "Create an offer",
            response = Category.class,
            nickname = "createOffer")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Offer created"),
            @ApiResponse(code = 500, message = "Offer not created")
    })
    public ResponseEntity<Offer> createOffer(@RequestBody Offer newOffer) throws NotCreatedException, SQLException {

        if (newOffer.getName().length() != 0) {
            offerService.createOffer(newOffer);
            return new ResponseEntity<Offer>(newOffer, HttpStatus.CREATED);
        } else {
            throw new NotCreatedException(ExceptionMessage.NULL_FIELDS);
        }
    }

    @PutMapping
    @ApiOperation(httpMethod = "PUT",
            value = "Update offer",
            response = Category.class,
            nickname = "updateOffer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Offer updated"),
            @ApiResponse(code = 404, message = "Offer not found"),
            @ApiResponse(code = 500, message = "Offer not updated")
    })
    public ResponseEntity<Offer> updateOffer(@RequestBody Offer offer) throws NotUpdatedException, SQLException {
        try {
            if (offer.getName().length() != 0 && offerService.findById(offer.getId()) != null) {
                Offer updatedOffer = offerService.updateOffer(offer);
                return new ResponseEntity<Offer>(updatedOffer, HttpStatus.OK);
            } else {
                throw new NotUpdatedException(ExceptionMessage.NULL_FIELDS);
            }
        } catch (NotFoundException e) {
            throw new NotUpdatedException(ExceptionMessage.NOT_UPDATED);
        }
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(httpMethod = "DELETE",
            value = "Delete an offer by id",
            response = Long.class,
            nickname = "deleteOffer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Offer deleted"),
            @ApiResponse(code = 404, message = "Offer not found"),
            @ApiResponse(code = 500, message = "Error")
    })
    public ResponseEntity<Long> deleteOffer(@PathVariable("id") Long id) throws NotDeletedException, SQLException {
        try {
            offerService.deleteOffer(id);
            return new ResponseEntity<Long>(id, HttpStatus.OK);
        } catch (Exception e) {
            throw new NotDeletedException(ExceptionMessage.NOT_DELETED);
        }

    }

    @PutMapping(value = "/{id}")
    @ApiOperation(httpMethod = "",
            value = "Set offer's availability",
            response = Long.class,
            nickname = "setAvailability"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Availability changed"),
            @ApiResponse(code = 404, message = "Offer not found"),
            @ApiResponse(code = 500, message = "Error")
    })
    public ResponseEntity<Long> setAvailability(@PathVariable("id") Long id, @RequestBody boolean availability) throws NotUpdatedException, SQLException {
        try {
            offerService.setAvailability(id, availability);
            return new ResponseEntity<Long>(id, HttpStatus.OK);
        } catch (Exception e) {
            throw new NotUpdatedException(ExceptionMessage.NOT_UPDATED);
        }
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(httpMethod = "GET",
            value = "Find offers by id",
            response = Offer.class,
            nickname = "findById"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 302, message = "Offer found"),
            @ApiResponse(code = 404, message = "Offer not found"),
            @ApiResponse(code = 500, message = "Error")
    })
    public ResponseEntity<Offer> findById(@PathVariable("id") Long id) throws NotFoundException, SQLException {
        Offer foundOffer = offerService.findById(id);
        return new ResponseEntity<Offer>(foundOffer, HttpStatus.FOUND);
    }

    @GetMapping
    @ApiOperation(httpMethod = "GET",
            value = "Find all offers",
            response = Offer.class,
            nickname = "findAll",
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 302, message = "Offers found"),
            @ApiResponse(code = 404, message = "Offers not found"),
            @ApiResponse(code = 500, message = "Error")
    })
    public ResponseEntity<List<Offer>> findAll() throws NotFoundException, SQLException {
        List<Offer> offerList = offerService.findAll();
        return new ResponseEntity<List<Offer>>(offerList, HttpStatus.FOUND);
    }

    @GetMapping(value = "/bytag/")
    @ApiOperation(httpMethod = "GET",
            value = "Find offers by tags",
            response = Offer.class,
            nickname = "findByTags",
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 302, message = "Offers found"),
            @ApiResponse(code = 404, message = "Offers not found"),
            @ApiResponse(code = 500, message = "Error")
    })
    public List<Offer> findByTags(@RequestBody List<Tag> tagList) throws NotFoundException, SQLException {
        return offerService.findByTags(tagList);
    }

    @GetMapping(value = "/available")
    @ApiOperation(httpMethod = "GET",
            value = "Find available offers",
            response = Offer.class,
            nickname = "findAvailableOffers",
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 302, message = "Offers found"),
            @ApiResponse(code = 404, message = "Offers not found"),
            @ApiResponse(code = 500, message = "Error")
    })
    public ResponseEntity<List<Offer>> findAvailableOffers() throws NotFoundException, SQLException {
        List<Offer> offerList = offerService.findAvailableOffers();
        return new ResponseEntity<List<Offer>>(offerList, HttpStatus.FOUND);
    }

    @PutMapping(value = "/{id}/addprice")
    @ApiOperation(httpMethod = "PUT",
            value = "Add price to offer",
            response = Long.class,
            nickname = "addPrice")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Price added"),
            @ApiResponse(code = 404, message = "Offer not found"),
            @ApiResponse(code = 500, message = "Error")
    })
    public ResponseEntity<Long> addPrice(@PathVariable("id") Long id, @RequestBody Price price) throws NotUpdatedException, SQLException {
        try {
            offerService.addPrice(id, price);
            return new ResponseEntity<Long>(id, HttpStatus.OK);
        } catch (Exception e) {

            throw new NotUpdatedException(ExceptionMessage.NOT_ADDED);
        }
    }

    @PutMapping(value = "/{id}/change")
    @ApiOperation(httpMethod = "PUT",
            value = "Change offer's price",
            response = Category.class,
            nickname = "changePrice")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Price updated"),
            @ApiResponse(code = 404, message = "Offer not found"),
            @ApiResponse(code = 500, message = "Error")
    })
    public ResponseEntity<Long> changePrice(@PathVariable("id") Long id, @RequestBody Price price) throws NotUpdatedException, SQLException {
        try {
            offerService.changePrice(id, price);
            return new ResponseEntity<Long>(id, HttpStatus.OK);
        } catch (Exception e) {
            throw new NotUpdatedException(ExceptionMessage.NOT_UPDATED);
        }
    }

    @GetMapping(value = "/pricefilter/")
    @ApiOperation(httpMethod = "GET",
            value = "Find offers between two prices",
            response = Offer.class,
            nickname = "getOfferByPriceFromTo",
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 302, message = "Offers found"),
            @ApiResponse(code = 404, message = "Offers not found"),
            @ApiResponse(code = 500, message = "Error")
    })
    public ResponseEntity<List<Offer>> getOfferByPriceFromTo(@RequestBody Price priceFrom, @RequestBody Price priceTo) throws NotFoundException, SQLException {
        List<Offer> offerList = offerService.getPriceFromTo(priceFrom, priceTo);
        return new ResponseEntity<List<Offer>>(offerList, HttpStatus.FOUND);
    }

    @PutMapping(value = "/{id}/addtag")
    @ApiOperation(httpMethod = "PUT",
            value = "Add a tag to offer",
            response = Long.class,
            nickname = "addTag")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tag added"),
            @ApiResponse(code = 404, message = "Offer not found"),
            @ApiResponse(code = 500, message = "Error")
    })
    public ResponseEntity<Long> addTag(@PathVariable("id") Long id, @RequestBody Tag tag) throws NotUpdatedException, SQLException {
        try {
            offerService.addTag(id, tag);
            return new ResponseEntity<Long>(id, HttpStatus.OK);
        } catch (Exception e) {
            throw new NotUpdatedException(ExceptionMessage.NOT_ADDED);
        }

    }

    @PutMapping(value = "/{id}/removetag")
    @ApiOperation(httpMethod = "PUT",
            value = "Remove a tag from offer",
            response = Long.class,
            nickname = "removeTag")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tag removed"),
            @ApiResponse(code = 404, message = "Tag not removed"),
            @ApiResponse(code = 500, message = "Error")
    })
    public ResponseEntity<Long> removeTag(@PathVariable("id") Long id, @RequestBody Tag tag) throws NotUpdatedException, SQLException, NotDeletedException {
        try {
            offerService.removeTag(id, tag);
            return new ResponseEntity<Long>(id, HttpStatus.OK);
        } catch (Exception e) {
            throw new NotDeletedException(ExceptionMessage.NOT_DELETED);
        }

    }

    @PutMapping(value = "/{id}/addcategory")
    @ApiOperation(httpMethod = "PUT",
            value = "Add a category to offer",
            response = Long.class,
            nickname = "addCategory")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Category added"),
            @ApiResponse(code = 404, message = "Offer not found"),
            @ApiResponse(code = 500, message = "Error")
    })
    public ResponseEntity<Long> addCategory(@PathVariable("id") Long id, @RequestBody Category category) throws NotUpdatedException, SQLException {
        try {
            offerService.addCategory(id, category);
            return new ResponseEntity<Long>(id, HttpStatus.OK);
        } catch (Exception e) {
            throw new NotUpdatedException(ExceptionMessage.NOT_ADDED);
        }
    }

    @PutMapping(value = "/{id}/removeCategory")
    @ApiOperation(httpMethod = "PUT",
            value = "Remove a category from offer",
            response = Long.class,
            nickname = "removeCategory")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Category removed"),
            @ApiResponse(code = 404, message = "Category not removed"),
            @ApiResponse(code = 500, message = "Error")
    })
    public ResponseEntity<Long> removeCategory(@PathVariable("id") Long id) throws NotDeletedException, SQLException, NotUpdatedException {
        try {
            offerService.removeCategory(id);
            return new ResponseEntity<Long>(id, HttpStatus.OK);
        } catch (Exception e) {
            throw new NotDeletedException(ExceptionMessage.NOT_DELETED);
        }
    }


    @GetMapping(value = "/categories/offers/filteredOffers")
    List<Offer> findFilteredOffers(@RequestBody OfferFilter offerFilter) throws SQLException, NotFoundException{
        List<Offer> offers = offerService.findFilteredOffers(offerFilter);
        return offers;
    }

}
