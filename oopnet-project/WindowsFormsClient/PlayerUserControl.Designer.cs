
namespace WindowsFormsClient
{
    partial class PlayerUserControl
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
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(PlayerUserControl));
            this.panel1 = new System.Windows.Forms.Panel();
            this.pbPlayer = new System.Windows.Forms.PictureBox();
            this.cbFavouritePlayer = new System.Windows.Forms.CheckBox();
            this.cbCaptain = new System.Windows.Forms.CheckBox();
            this.lblPosition = new System.Windows.Forms.Label();
            this.lblShirtNumber = new System.Windows.Forms.Label();
            this.lblName = new System.Windows.Forms.Label();
            this.cmsPlayerAddFavourites = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.addToFavouritesToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.cmsPlayerRemoveFavourites = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.removeFromFavouritesToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.panel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pbPlayer)).BeginInit();
            this.cmsPlayerAddFavourites.SuspendLayout();
            this.cmsPlayerRemoveFavourites.SuspendLayout();
            this.SuspendLayout();
            // 
            // panel1
            // 
            resources.ApplyResources(this.panel1, "panel1");
            this.panel1.BackColor = System.Drawing.SystemColors.ControlLight;
            this.panel1.Controls.Add(this.pbPlayer);
            this.panel1.Controls.Add(this.cbFavouritePlayer);
            this.panel1.Controls.Add(this.cbCaptain);
            this.panel1.Controls.Add(this.lblPosition);
            this.panel1.Controls.Add(this.lblShirtNumber);
            this.panel1.Controls.Add(this.lblName);
            this.panel1.Name = "panel1";
            this.panel1.Click += new System.EventHandler(this.panel1_Click);
            this.panel1.MouseDown += new System.Windows.Forms.MouseEventHandler(this.panel1_MouseDown);
            // 
            // pbPlayer
            // 
            resources.ApplyResources(this.pbPlayer, "pbPlayer");
            this.pbPlayer.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.pbPlayer.Name = "pbPlayer";
            this.pbPlayer.TabStop = false;
            this.pbPlayer.Click += new System.EventHandler(this.pbPlayer_Click);
            this.pbPlayer.MouseEnter += new System.EventHandler(this.pbPlayer_MouseEnter);
            this.pbPlayer.MouseLeave += new System.EventHandler(this.pbPlayer_MouseLeave);
            // 
            // cbFavouritePlayer
            // 
            resources.ApplyResources(this.cbFavouritePlayer, "cbFavouritePlayer");
            this.cbFavouritePlayer.Name = "cbFavouritePlayer";
            this.cbFavouritePlayer.UseVisualStyleBackColor = true;
            // 
            // cbCaptain
            // 
            resources.ApplyResources(this.cbCaptain, "cbCaptain");
            this.cbCaptain.Name = "cbCaptain";
            this.cbCaptain.UseVisualStyleBackColor = true;
            // 
            // lblPosition
            // 
            resources.ApplyResources(this.lblPosition, "lblPosition");
            this.lblPosition.Name = "lblPosition";
            // 
            // lblShirtNumber
            // 
            resources.ApplyResources(this.lblShirtNumber, "lblShirtNumber");
            this.lblShirtNumber.Name = "lblShirtNumber";
            // 
            // lblName
            // 
            resources.ApplyResources(this.lblName, "lblName");
            this.lblName.Name = "lblName";
            // 
            // cmsPlayerAddFavourites
            // 
            resources.ApplyResources(this.cmsPlayerAddFavourites, "cmsPlayerAddFavourites");
            this.cmsPlayerAddFavourites.ImageScalingSize = new System.Drawing.Size(28, 28);
            this.cmsPlayerAddFavourites.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.addToFavouritesToolStripMenuItem});
            this.cmsPlayerAddFavourites.Name = "cmsPlayerAddFavourites";
            // 
            // addToFavouritesToolStripMenuItem
            // 
            resources.ApplyResources(this.addToFavouritesToolStripMenuItem, "addToFavouritesToolStripMenuItem");
            this.addToFavouritesToolStripMenuItem.Name = "addToFavouritesToolStripMenuItem";
            this.addToFavouritesToolStripMenuItem.Click += new System.EventHandler(this.addToFavouritesToolStripMenuItem_Click);
            // 
            // cmsPlayerRemoveFavourites
            // 
            resources.ApplyResources(this.cmsPlayerRemoveFavourites, "cmsPlayerRemoveFavourites");
            this.cmsPlayerRemoveFavourites.ImageScalingSize = new System.Drawing.Size(28, 28);
            this.cmsPlayerRemoveFavourites.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.removeFromFavouritesToolStripMenuItem});
            this.cmsPlayerRemoveFavourites.Name = "cmsPlayerRemoveFavourites";
            // 
            // removeFromFavouritesToolStripMenuItem
            // 
            resources.ApplyResources(this.removeFromFavouritesToolStripMenuItem, "removeFromFavouritesToolStripMenuItem");
            this.removeFromFavouritesToolStripMenuItem.Name = "removeFromFavouritesToolStripMenuItem";
            this.removeFromFavouritesToolStripMenuItem.Click += new System.EventHandler(this.removeFromFavouritesToolStripMenuItem_Click);
            // 
            // PlayerUserControl
            // 
            resources.ApplyResources(this, "$this");
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.panel1);
            this.Name = "PlayerUserControl";
            this.panel1.ResumeLayout(false);
            this.panel1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pbPlayer)).EndInit();
            this.cmsPlayerAddFavourites.ResumeLayout(false);
            this.cmsPlayerRemoveFavourites.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.PictureBox pbPlayer;
        private System.Windows.Forms.CheckBox cbFavouritePlayer;
        private System.Windows.Forms.CheckBox cbCaptain;
        private System.Windows.Forms.Label lblPosition;
        private System.Windows.Forms.Label lblShirtNumber;
        private System.Windows.Forms.Label lblName;
        private System.Windows.Forms.ContextMenuStrip cmsPlayerAddFavourites;
        private System.Windows.Forms.ContextMenuStrip cmsPlayerRemoveFavourites;
        private System.Windows.Forms.ToolStripMenuItem addToFavouritesToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem removeFromFavouritesToolStripMenuItem;
    }
}
