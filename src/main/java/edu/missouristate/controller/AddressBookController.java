//Alexander Hansen
package edu.missouristate.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import edu.missouristate.domain.Address;
import edu.missouristate.service.AddressService;

@Controller
public class AddressBookController {

	@Autowired
	AddressService addressService;

	@GetMapping("/")
	public String getIndex(Model model) {
		List<Address> addressList = addressService.getAddresses();
		model.addAttribute("addressList", addressList);
		return "index";
	}

	@GetMapping("/addEditAddress")
	public String getAddEditAddress(Model model, Integer addressId, HttpSession session,
			@ModelAttribute("message") String message, @ModelAttribute("messageType") String messageType) {
		model.addAttribute("heading", "Add Contact");
		Address address = null;
		if (addressId != null) {
			address = addressService.getAddressById(addressId);
		}
		if (address != null) {
			model.addAttribute("heading", "Edit Contact");
			model.addAttribute("address", address);
		} else {
			session.setAttribute("message", "Sorry, unable to find a " + " contact with that addressId");
			session.setAttribute("messageType", "danger");
		}

		return "addEditAddress";
	}

	@PostMapping("/addEditAddress")
	public String postAddEditAddress(HttpSession session, Address address) {
		try {
			String message = (address != null && address.getAddressId() != null) ? "contact Updated Succesfully"
					: "Contact Added Successfully";
			addressService.saveAddress(address);
			session.setAttribute("messageTyp", message);
			session.setAttribute("messageType", "success");
			return "redirect:/";
		} catch (Exception e) {
			session.setAttribute("messageType", "danger");
			session.setAttribute("message",
					"There was an error processing your request. " + "Error = " + e.getMessage());
			return "redirect:/addEditAddress";
		}
	}

	@GetMapping("/deleteAddress")
	public String getDeleteAddress(Model model, HttpSession session, Integer addressId) {

		try {
			if (addressId != null) {
				addressService.deleteAddress(addressId);
				session.setAttribute("message", "Contact Record Successfully deleted");

			} else {
				session.setAttribute("message", "Sorry unable to find a " + " contact with that address ID");
				session.setAttribute("messageType", "danger");

			}
		} catch (Exception e) {
			session.setAttribute("messageType", "danger");
			session.setAttribute("message",
					"There was an error processing your request. " + "Error = " + e.getMessage());
		}

		return "redirect:/";

	}
}
