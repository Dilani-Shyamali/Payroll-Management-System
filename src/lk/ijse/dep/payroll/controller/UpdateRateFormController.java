package lk.ijse.dep.payroll.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dep.payroll.Buisness.BOFactory;
import lk.ijse.dep.payroll.Buisness.BOTypes;
import lk.ijse.dep.payroll.Buisness.custom.EmployeeBO;
import lk.ijse.dep.payroll.Buisness.custom.RateBO;
import lk.ijse.dep.payroll.dto.RateDTO;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateRateFormController {
    public AnchorPane acrUpdateRate;
    public Label lblDate;
    public JFXButton btnUpdate;
    public Label lblRateID;
    public JFXTextField txtEPFEmp;
    public JFXTextField txtEPFCompany;
    public JFXTextField txtETF;

    private RateBO rateBO = BOFactory.getInstance().getBO(BOTypes.RATE);

    public void initialize() throws Exception {
        int maxId = 0;
        try {
            String lastRateId = rateBO.getLastRateID();
            System.out.println(lastRateId);

            if (lastRateId == null) {
                maxId = 0;
            } else {
                maxId = Integer.parseInt(lastRateId.replace("R", ""));
            }

            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "R00" + maxId;
            } else if (maxId < 100) {
                id = "R0" + maxId;
            } else {
                id = "R" + maxId;
            }
            lblRateID.setText(id);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR , "Something went wrong, Please contact software company").show();
            Logger.getLogger("lk.ijse.dep.payroll.controller").log(Level.SEVERE, null , e);
        }

        lblDate.setText(LocalDate.now().toString());
    }
    public void btnNewSalary_OnAction(ActionEvent actionEvent) throws Exception {
        RateDTO newRate = new RateDTO(lblRateID.getText(),
                Date.valueOf(lblDate.getText()),
                Double.valueOf(txtETF.getText()),
                Double.valueOf(txtEPFEmp.getText()),
                Double.valueOf(txtEPFCompany.getText()));
        rateBO.saveRate(newRate);
    }
}
