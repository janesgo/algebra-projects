
namespace WindowsFormsClient
{
    partial class Settings
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Settings));
            this.gbSelectLanguage = new System.Windows.Forms.GroupBox();
            this.rbCroatian = new System.Windows.Forms.RadioButton();
            this.rbEnglish = new System.Windows.Forms.RadioButton();
            this.gbSelectGender = new System.Windows.Forms.GroupBox();
            this.rbMen = new System.Windows.Forms.RadioButton();
            this.rbWomen = new System.Windows.Forms.RadioButton();
            this.lblSettings = new System.Windows.Forms.Label();
            this.btnOK = new System.Windows.Forms.Button();
            this.btnCancel = new System.Windows.Forms.Button();
            this.gbSelectAccessType = new System.Windows.Forms.GroupBox();
            this.rbFile = new System.Windows.Forms.RadioButton();
            this.rbWeb = new System.Windows.Forms.RadioButton();
            this.gbSelectLanguage.SuspendLayout();
            this.gbSelectGender.SuspendLayout();
            this.gbSelectAccessType.SuspendLayout();
            this.SuspendLayout();
            // 
            // gbSelectLanguage
            // 
            resources.ApplyResources(this.gbSelectLanguage, "gbSelectLanguage");
            this.gbSelectLanguage.Controls.Add(this.rbCroatian);
            this.gbSelectLanguage.Controls.Add(this.rbEnglish);
            this.gbSelectLanguage.Name = "gbSelectLanguage";
            this.gbSelectLanguage.TabStop = false;
            // 
            // rbCroatian
            // 
            resources.ApplyResources(this.rbCroatian, "rbCroatian");
            this.rbCroatian.Checked = true;
            this.rbCroatian.Name = "rbCroatian";
            this.rbCroatian.TabStop = true;
            this.rbCroatian.Tag = "Croatian";
            this.rbCroatian.UseVisualStyleBackColor = true;
            // 
            // rbEnglish
            // 
            resources.ApplyResources(this.rbEnglish, "rbEnglish");
            this.rbEnglish.Name = "rbEnglish";
            this.rbEnglish.Tag = "English";
            this.rbEnglish.UseVisualStyleBackColor = true;
            // 
            // gbSelectGender
            // 
            resources.ApplyResources(this.gbSelectGender, "gbSelectGender");
            this.gbSelectGender.Controls.Add(this.rbMen);
            this.gbSelectGender.Controls.Add(this.rbWomen);
            this.gbSelectGender.Name = "gbSelectGender";
            this.gbSelectGender.TabStop = false;
            // 
            // rbMen
            // 
            resources.ApplyResources(this.rbMen, "rbMen");
            this.rbMen.Checked = true;
            this.rbMen.Name = "rbMen";
            this.rbMen.TabStop = true;
            this.rbMen.Tag = "Men";
            this.rbMen.UseVisualStyleBackColor = true;
            // 
            // rbWomen
            // 
            resources.ApplyResources(this.rbWomen, "rbWomen");
            this.rbWomen.Name = "rbWomen";
            this.rbWomen.Tag = "Women";
            this.rbWomen.UseVisualStyleBackColor = true;
            // 
            // lblSettings
            // 
            resources.ApplyResources(this.lblSettings, "lblSettings");
            this.lblSettings.Name = "lblSettings";
            // 
            // btnOK
            // 
            resources.ApplyResources(this.btnOK, "btnOK");
            this.btnOK.DialogResult = System.Windows.Forms.DialogResult.Cancel;
            this.btnOK.Name = "btnOK";
            this.btnOK.UseVisualStyleBackColor = true;
            this.btnOK.Click += new System.EventHandler(this.btnOK_Click);
            // 
            // btnCancel
            // 
            resources.ApplyResources(this.btnCancel, "btnCancel");
            this.btnCancel.DialogResult = System.Windows.Forms.DialogResult.Cancel;
            this.btnCancel.Name = "btnCancel";
            this.btnCancel.UseVisualStyleBackColor = true;
            this.btnCancel.Click += new System.EventHandler(this.btnCancel_Click);
            // 
            // gbSelectAccessType
            // 
            resources.ApplyResources(this.gbSelectAccessType, "gbSelectAccessType");
            this.gbSelectAccessType.Controls.Add(this.rbFile);
            this.gbSelectAccessType.Controls.Add(this.rbWeb);
            this.gbSelectAccessType.Name = "gbSelectAccessType";
            this.gbSelectAccessType.TabStop = false;
            // 
            // rbFile
            // 
            resources.ApplyResources(this.rbFile, "rbFile");
            this.rbFile.Checked = true;
            this.rbFile.Name = "rbFile";
            this.rbFile.TabStop = true;
            this.rbFile.Tag = "File";
            this.rbFile.UseVisualStyleBackColor = true;
            // 
            // rbWeb
            // 
            resources.ApplyResources(this.rbWeb, "rbWeb");
            this.rbWeb.Name = "rbWeb";
            this.rbWeb.Tag = "Web";
            this.rbWeb.UseVisualStyleBackColor = true;
            // 
            // Settings
            // 
            this.AcceptButton = this.btnOK;
            resources.ApplyResources(this, "$this");
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.CancelButton = this.btnCancel;
            this.Controls.Add(this.gbSelectAccessType);
            this.Controls.Add(this.btnCancel);
            this.Controls.Add(this.btnOK);
            this.Controls.Add(this.lblSettings);
            this.Controls.Add(this.gbSelectLanguage);
            this.Controls.Add(this.gbSelectGender);
            this.Name = "Settings";
            this.gbSelectLanguage.ResumeLayout(false);
            this.gbSelectLanguage.PerformLayout();
            this.gbSelectGender.ResumeLayout(false);
            this.gbSelectGender.PerformLayout();
            this.gbSelectAccessType.ResumeLayout(false);
            this.gbSelectAccessType.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.GroupBox gbSelectLanguage;
        private System.Windows.Forms.RadioButton rbCroatian;
        private System.Windows.Forms.RadioButton rbEnglish;
        private System.Windows.Forms.GroupBox gbSelectGender;
        private System.Windows.Forms.RadioButton rbMen;
        private System.Windows.Forms.RadioButton rbWomen;
        private System.Windows.Forms.Label lblSettings;
        private System.Windows.Forms.Button btnOK;
        private System.Windows.Forms.Button btnCancel;
        private System.Windows.Forms.GroupBox gbSelectAccessType;
        private System.Windows.Forms.RadioButton rbFile;
        private System.Windows.Forms.RadioButton rbWeb;
    }
}