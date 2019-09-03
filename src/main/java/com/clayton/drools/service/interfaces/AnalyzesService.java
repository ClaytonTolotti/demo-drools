package com.clayton.drools.service.interfaces;

import com.clayton.drools.dto.AnalyzesDTO;
import com.clayton.drools.dto.DemoRequest;

public interface AnalyzesService {

    AnalyzesDTO analyzeProfile(DemoRequest body);
}
