package com.cloud.app.report.fallback;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cloud.app.report.service.ReportDownFeginClient;

@Component
public class ReportDownFeginClientFallback implements ReportDownFeginClient{

	@Override
	public ResponseEntity<byte[]> broadbandExportExcel(Map<String, Object> map) {
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<byte[]> installExportExcel(Map<String, Object> map) {
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<byte[]> installExportExcelPQ(Map<String, Object> map) {
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<byte[]> obstacleExportExcel(Map<String, Object> map) {
		return ResponseEntity.noContent().build();
	}

}
