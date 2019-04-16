package org.launchcode.controllers;

import org.launchcode.models.Job;
import org.launchcode.models.forms.JobForm;
import org.launchcode.models.data.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "job")
public class JobController {

    private JobData jobData = JobData.getInstance();

    // The detail display for a given Job at URLs like /job?id=17
//    @RequestMapping(value="edit/{cheeseID}", method = RequestMethod.GET)
//    public String displayEditForm(Model model, @PathVariable int cheeseID){
//        Cheese cheeseToEdit = CheeseData.getByID(cheeseID);
//        if(cheeseToEdit == null) {
//            return "redirect:";
//        }
//        model.addAttribute("title"," Edit Cheeses");
//        model.addAttribute("cheese", cheeseToEdit);
//        model.addAttribute("cheeseTypes", CheeseType.values());
//        return "cheese/edit";
//    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, @RequestParam("id") int jobID) {

        // TODO #1 - get the Job with the given ID and pass it into the view
        Job jobByID = jobData.findById(jobID);
        //
        model.addAttribute("job", jobByID);
        return "job-detail";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new JobForm());
        return "new-job";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @Valid JobForm jobForm, Errors errors) {

        // TODO #6 - Validate the JobForm model, and if valid, create a
        // new Job and add it to the jobData data store. Then
        // redirect to the job detail view for the new Job.

        return "";

    }
}
