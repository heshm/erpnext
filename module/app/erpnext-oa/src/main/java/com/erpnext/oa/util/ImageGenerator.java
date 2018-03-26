package com.erpnext.oa.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.flowable.bpmn.model.BpmnModel;
import org.flowable.cmmn.image.CaseDiagramGenerator;
import org.flowable.cmmn.image.impl.DefaultCaseDiagramGenerator;
import org.flowable.cmmn.model.CmmnModel;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.image.exception.FlowableImageException;
import org.flowable.image.impl.DefaultProcessDiagramGenerator;

public class ImageGenerator {
	
	public static BufferedImage createImage(BpmnModel bpmnModel) {
        ProcessDiagramGenerator diagramGenerator = new DefaultProcessDiagramGenerator();
        BufferedImage diagramImage = diagramGenerator.generatePngImage(bpmnModel, 1.0);
        return diagramImage;
    }

    public static BufferedImage createImage(BpmnModel bpmnModel, double scaleFactor) {
        ProcessDiagramGenerator diagramGenerator = new DefaultProcessDiagramGenerator(scaleFactor);
        BufferedImage diagramImage = diagramGenerator.generatePngImage(bpmnModel, scaleFactor);
        return diagramImage;
    }
    
    public static BufferedImage createCmmnImage(CmmnModel cmmnModel, double scaleFactor) {
        CaseDiagramGenerator diagramGenerator = new DefaultCaseDiagramGenerator(scaleFactor);
        BufferedImage diagramImage = diagramGenerator.generatePngImage(cmmnModel, scaleFactor);
        return diagramImage;
    }

    public static byte[] createByteArrayForImage(BufferedImage image, String imageType) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, imageType, out);

        } catch (IOException e) {
            throw new FlowableImageException("Error while generating byte array for process image", e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ignore) {
                // Exception is silently ignored
            }
        }
        return out.toByteArray();
    }
}
