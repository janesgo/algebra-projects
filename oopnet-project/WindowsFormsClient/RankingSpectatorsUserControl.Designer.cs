
namespace WindowsFormsClient
{
    partial class RankingSpectatorsUserControl
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

        #region Component Designer generated code

        /// <summary> 
        /// Required method for Designer support - do not modify 
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.lblPlace = new System.Windows.Forms.Label();
            this.lblHome = new System.Windows.Forms.Label();
            this.lblLocation = new System.Windows.Forms.Label();
            this.lblAttendance = new System.Windows.Forms.Label();
            this.lblAway = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // lblPlace
            // 
            this.lblPlace.AutoSize = true;
            this.lblPlace.Location = new System.Drawing.Point(3, 9);
            this.lblPlace.Name = "lblPlace";
            this.lblPlace.Size = new System.Drawing.Size(35, 13);
            this.lblPlace.TabIndex = 0;
            this.lblPlace.Text = "label1";
            this.lblPlace.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // lblHome
            // 
            this.lblHome.AutoSize = true;
            this.lblHome.Location = new System.Drawing.Point(332, 9);
            this.lblHome.Name = "lblHome";
            this.lblHome.Size = new System.Drawing.Size(35, 13);
            this.lblHome.TabIndex = 1;
            this.lblHome.Text = "label2";
            this.lblHome.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // lblLocation
            // 
            this.lblLocation.AutoSize = true;
            this.lblLocation.Location = new System.Drawing.Point(44, 9);
            this.lblLocation.Name = "lblLocation";
            this.lblLocation.Size = new System.Drawing.Size(35, 13);
            this.lblLocation.TabIndex = 2;
            this.lblLocation.Text = "label3";
            this.lblLocation.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // lblAttendance
            // 
            this.lblAttendance.AutoSize = true;
            this.lblAttendance.Location = new System.Drawing.Point(226, 9);
            this.lblAttendance.Name = "lblAttendance";
            this.lblAttendance.Size = new System.Drawing.Size(35, 13);
            this.lblAttendance.TabIndex = 3;
            this.lblAttendance.Text = "label4";
            this.lblAttendance.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // lblAway
            // 
            this.lblAway.AutoSize = true;
            this.lblAway.Location = new System.Drawing.Point(463, 9);
            this.lblAway.Name = "lblAway";
            this.lblAway.Size = new System.Drawing.Size(35, 13);
            this.lblAway.TabIndex = 4;
            this.lblAway.Text = "label5";
            this.lblAway.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // RankingSpectatorsUserControl
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.lblAway);
            this.Controls.Add(this.lblAttendance);
            this.Controls.Add(this.lblLocation);
            this.Controls.Add(this.lblHome);
            this.Controls.Add(this.lblPlace);
            this.Name = "RankingSpectatorsUserControl";
            this.Size = new System.Drawing.Size(600, 30);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label lblPlace;
        private System.Windows.Forms.Label lblHome;
        private System.Windows.Forms.Label lblLocation;
        private System.Windows.Forms.Label lblAttendance;
        private System.Windows.Forms.Label lblAway;
    }
}
