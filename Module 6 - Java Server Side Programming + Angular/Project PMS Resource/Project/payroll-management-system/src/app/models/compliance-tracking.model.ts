export interface ComplianceTracking {
  id: number;
  employeeId: number;
  complianceType: string;
  status: 'Compliant' | 'Non-compliant';
  dueDate: Date;
  completionDate?: Date;
}
