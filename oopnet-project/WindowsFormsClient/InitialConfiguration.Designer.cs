
namespace WindowsFormsClient
{
    partial class InitialConfiguration
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
            this.rbMen = new System.Windows.Forms.RadioButton();
            this.rbWomen = new System.Windows.Forms.RadioButton();
            this.gbSelectGender = new System.Windows.Forms.GroupBox();
            this.gbSelectLanguage = new System.Windows.Forms.GroupBox();
            this.rbCroatian = new System.Windows.Forms.RadioButton();
            this.rbEnglish = new System.Windows.Forms.RadioButton();
            this.btnStartApp = new System.Windows.Forms.Button();
            this.gbSelectAccessType = new System.Windows.Forms.GroupBox();
            this.rbFile = new System.Windows.Forms.RadioButton();
            this.rbWeb = new System.Windows.Forms.RadioButton();
            this.gbSelectGender.SuspendLayout();
            this.gbSelectLanguage.SuspendLayout();
            this.gbSelectAccessType.SuspendLayout();
            this.SuspendLayout();
            // 
            // rbMen
            // 
            this.rbMen.AutoSize = true;
            this.rbMen.Checked = true;
            this.rbMen.Location = new System.Drawing.Point(11, 35);
            this.rbMen.Margin = new System.Windows.Forms.Padding(6, 6, 6, 6);
            this.rbMen.Name = "rbMen";
            this.rbMen.Size = new System.Drawing.Size(76, 29);
            this.rbMen.TabIndex = 3;
            this.rbMen.TabStop = true;
            this.rbMen.Tag = "1";
            this.rbMen.Text = "Men";
            this.rbMen.UseVisualStyleBackColor = true;
            // 
            // rbWomen
            // 
            this.rbWomen.AutoSize = true;
            this.rbWomen.Location = new System.Drawing.Point(11, 78);
            this.rbWomen.Margin = new System.Windows.Forms.Padding(6, 6, 6, 6);
            this.rbWomen.Name = "rbWomen";
            this.rbWomen.Size = new System.Drawing.Size(106, 29);
            this.rbWomen.TabIndex = 4;
            this.rbWomen.Tag = "2";
            this.rbWomen.Text = "Women";
            this.rbWomen.UseVisualStyleBackColor = true;
            // 
            // gbSelectGender
            // 
            this.gbSelectGender.Controls.Add(this.rbMen);
            this.gbSelectGender.Controls.Add(this.rbWomen);
            this.gbSelectGender.Location = new System.Drawing.Point(167, 15);
            this.gbSelectGender.Margin = new System.Windows.Forms.Padding(6, 6, 6, 6);
            this.gbSelectGender.Name = "gbSelectGender";
            this.gbSelectGender.Padding = new System.Windows.Forms.Padding(6, 6, 6, 6);
            this.gbSelectGender.Size = new System.Drawing.Size(372, 137);
            this.gbSelectGender.TabIndex = 5;
            this.gbSelectGender.TabStop = false;
            this.gbSelectGender.Text = "Select gender:";
            // 
            // gbSelectLanguage
            // 
            this.gbSelectLanguage.Controls.Add(this.rbCroatian);
            this.gbSelectLanguage.Controls.Add(this.rbEnglish);
            this.gbSelectLanguage.Location = new System.Drawing.Point(167, 321);
            this.gbSelectLanguage.Margin = new System.Windows.Forms.Padding(6, 6, 6, 6);
            this.gbSelectLanguage.Name = "gbSelectLanguage";
            this.gbSelectLanguage.Padding = new System.Windows.Forms.Padding(6, 6, 6, 6);
            this.gbSelectLanguage.Size = new System.Drawing.Size(372, 137);
            this.gbSelectLanguage.TabIndex = 6;
            this.gbSelectLanguage.TabStop = false;
            this.gbSelectLanguage.Text = "Select language:";
            // 
            // rbCroatian
            // 
            this.rbCroatian.AutoSize = true;
            this.rbCroatian.Checked = true;
            this.rbCroatian.Location = new System.Drawing.Point(11, 35);
            this.rbCroatian.Margin = new System.Windows.Forms.Padding(6, 6, 6, 6);
            this.rbCroatian.Name = "rbCroatian";
            this.rbCroatian.Size = new System.Drawing.Size(111, 29);
            this.rbCroatian.TabIndex = 3;
            this.rbCroatian.TabStop = true;
            this.rbCroatian.Tag = "1";
            this.rbCroatian.Text = "Croatian";
            this.rbCroatian.UseVisualStyleBackColor = true;
            // 
            // rbEnglish
            // 
            this.rbEnglish.AutoSize = true;
            this.rbEnglish.Location = new System.Drawing.Point(11, 78);
            this.rbEnglish.Margin = new System.Windows.Forms.Padding(6, 6, 6, 6);
            this.rbEnglish.Name = "rbEnglish";
            this.rbEnglish.Size = new System.Drawing.Size(101, 29);
            this.rbEnglish.TabIndex = 4;
            this.rbEnglish.Tag = "2";
            this.rbEnglish.Text = "English";
            this.rbEnglish.UseVisualStyleBackColor = true;
            // 
            // btnStartApp
            // 
            this.btnStartApp.DialogResult = System.Windows.Forms.DialogResult.OK;
            this.btnStartApp.Location = new System.Drawing.Point(259, 474);
            this.btnStartApp.Margin = new System.Windows.Forms.Padding(6, 6, 6, 6);
            this.btnStartApp.Name = "btnStartApp";
            this.btnStartApp.Size = new System.Drawing.Size(183, 55);
            this.btnStartApp.TabIndex = 7;
            this.btnStartApp.Text = "Start application";
            this.btnStartApp.UseVisualStyleBackColor = true;
            this.btnStartApp.Click += new System.EventHandler(this.btnStartApp_Click);
            // 
            // gbSelectAccessType
            // 
            this.gbSelectAccessType.Controls.Add(this.rbFile);
            this.gbSelectAccessType.Controls.Add(this.rbWeb);
            this.gbSelectAccessType.Location = new System.Drawing.Point(167, 168);
            this.gbSelectAccessType.Margin = new System.Windows.Forms.Padding(6);
            this.gbSelectAccessType.Name = "gbSelectAccessType";
            this.gbSelectAccessType.Padding = new System.Windows.Forms.Padding(6);
            this.gbSelectAccessType.Size = new System.Drawing.Size(372, 137);
            this.gbSelectAccessType.TabIndex = 6;
            this.gbSelectAccessType.TabStop = false;
            this.gbSelectAccessType.Text = "Select data repository:";
            // 
            // rbFile
            // 
            this.rbFile.AutoSize = true;
            this.rbFile.Checked = true;
            this.rbFile.Location = new System.Drawing.Point(11, 35);
            this.rbFile.Margin = new System.Windows.Forms.Padding(6);
            this.rbFile.Name = "rbFile";
            this.rbFile.Size = new System.Drawing.Size(68, 29);
            this.rbFile.TabIndex = 3;
            this.rbFile.TabStop = true;
            this.rbFile.Tag = "1";
            this.rbFile.Text = "File";
            this.rbFile.UseVisualStyleBackColor = true;
            // 
            // rbWeb
            // 
            this.rbWeb.AutoSize = true;
            this.rbWeb.Location = new System.Drawing.Point(11, 78);
            this.rbWeb.Margin = new System.Windows.Forms.Padding(6);
            this.rbWeb.Name = "rbWeb";
            this.rbWeb.Size = new System.Drawing.Size(79, 29);
            this.rbWeb.TabIndex = 4;
            this.rbWeb.Tag = "2";
            this.rbWeb.Text = "Web";
            this.rbWeb.UseVisualStyleBackColor = true;
            // 
            // InitialConfiguration
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(11F, 24F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(704, 544);
            this.Controls.Add(this.gbSelectAccessType);
            this.Controls.Add(this.btnStartApp);
            this.Controls.Add(this.gbSelectLanguage);
            this.Controls.Add(this.gbSelectGender);
            this.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.Name = "InitialConfiguration";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Project - Initial Configuration";
            this.gbSelectGender.ResumeLayout(false);
            this.gbSelectGender.PerformLayout();
            this.gbSelectLanguage.ResumeLayout(false);
            this.gbSelectLanguage.PerformLayout();
            this.gbSelectAccessType.ResumeLayout(false);
            this.gbSelectAccessType.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion
        private System.Windows.Forms.RadioButton rbMen;
        private System.Windows.Forms.RadioButton rbWomen;
        private System.Windows.Forms.GroupBox gbSelectGender;
        private System.Windows.Forms.GroupBox gbSelectLanguage;
        private System.Windows.Forms.RadioButton rbCroatian;
        private System.Windows.Forms.RadioButton rbEnglish;
        private System.Windows.Forms.Button btnStartApp;
        private System.Windows.Forms.GroupBox gbSelectAccessType;
        private System.Windows.Forms.RadioButton rbFile;
        private System.Windows.Forms.RadioButton rbWeb;
    }
}