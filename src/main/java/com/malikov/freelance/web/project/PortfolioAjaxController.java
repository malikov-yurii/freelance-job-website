package com.malikov.freelance.web.project;

import com.malikov.freelance.to.ProjectSmallTo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Malikov on 4/17/2017.
 */
@RestController
@RequestMapping(value = "/ajax/profile/projects/portfolio", produces = MediaType.APPLICATION_JSON_VALUE)
public class PortfolioAjaxController extends AbstractProjectController {

    /*//    @GetMapping(value = "/portfolio/{id}/", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping()
//    public List<ProjectSmallTo> getPortfolio(@PathVariable("id") int freelancerId) {
    public List<ProjectSmallTo> getPortfolio() {
        return super.getPortfolio(1);
    }*/
}
